package wwon.seokk.abandonedpets.ui.kind

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import wwon.seokk.abandonedpets.R
import wwon.seokk.abandonedpets.domain.entity.kind.KindResultEntity
import wwon.seokk.abandonedpets.ui.base.ScreenState
import wwon.seokk.abandonedpets.ui.common.*
import wwon.seokk.abandonedpets.ui.home.HomeViewModel
import wwon.seokk.abandonedpets.ui.theme.AbandonedPetsTheme

/**
 * Created by WonSeok on 2022.08.23
 **/
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PetKindScreen(
    parentViewModel: HomeViewModel,
    petKindViewModel: PetKindViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    fun onSelectKind(query: KindResultEntity, field: SheetField) {
        petKindViewModel.selectKind(query, field)
    }
    // Status bar styling now handled by edge-to-edge behavior and theme

    val lifecycleOwner = LocalLifecycleOwner.current
    val stateFlow = petKindViewModel.uiState()
    val stateLifecycleAware = remember(lifecycleOwner, stateFlow) {
        stateFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val state by stateLifecycleAware.collectAsState(initial = petKindViewModel.createInitialState())

    val scaffoldState = rememberScaffoldState()
    val bottomState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden, skipHalfExpanded = true)

    val errorMsg = stringResource(id = R.string.common_network_error_message)

    LaunchedEffect(petKindViewModel.uiSideEffect()) {
        val messageHost = SnackBarView(this)
        petKindViewModel.uiSideEffect().collect { uiSideEffect ->
            when (uiSideEffect) {
                is PetKindSideEffect.ShowSnackBar -> {
                    messageHost.showSnackBar(
                        snackBarHostState = scaffoldState.snackbarHostState,
                        message = uiSideEffect.message
                    )
                }
                is PetKindSideEffect.ShowNetworkError -> {
                    messageHost.showSnackBar(
                        snackBarHostState = scaffoldState.snackbarHostState,
                        message = errorMsg
                    )
                    bottomState.hide()
                }
            }
        }
    }

    ModalBottomSheetLayout(
        modifier = Modifier.statusBarsPadding(),
        sheetState = bottomState,
        sheetShape =  AbandonedPetsTheme.shapes.bottomSheetShape,
        sheetBackgroundColor = AbandonedPetsTheme.colors.surfaceColor,
        sheetContent = {
            BottomContent(
                uiState = state,
                bottomState = bottomState,
                onSelectKind = { query, field -> onSelectKind(query, field) }
            )
        }
    ) {
        Scaffold(
            modifier = Modifier.navigationBarsPadding(),
            scaffoldState = scaffoldState,
            topBar = {
                NavigateUpAppBar(navigateBack = navigateBack)
            },
            content = {
                MainContent(
                    modifier = Modifier.padding(it),
                    uiState = state,
                    bottomState = bottomState,
                    parentViewModel = parentViewModel,
                    petKindViewModel = petKindViewModel,
                    navigateBack = navigateBack
                )
            }
        )
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomContent(
    uiState: PetKindState,
    bottomState: ModalBottomSheetState,
    onSelectKind: (KindResultEntity, SheetField) -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
    ) {
        Column(Modifier.background(color = AbandonedPetsTheme.colors.surfaceColor)) {
            NoticeTitle(contentText = stringResource(id = R.string.kind_screen_select_message))
            SelectListing(uiState, bottomState, onSelectKind)
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun MainContent(
    modifier: Modifier,
    uiState: PetKindState?,
    bottomState: ModalBottomSheetState?,
    parentViewModel: HomeViewModel?,
    petKindViewModel: PetKindViewModel?,
    navigateBack: () -> Unit
) {
    Column(
        modifier = modifier
            .background(color = AbandonedPetsTheme.colors.surfaceColor)
            .padding(15.dp)
            .fillMaxSize()
    ) {
        NoticeTitle(contentText = stringResource(id = R.string.kind_screen_questions_message))
        DropDownTextField(
            labelText = stringResource(id = R.string.kind_screen_animals),
            value = uiState?.selectedUpKind?.value?.knm ?: stringResource(id = R.string.common_all),
            bottomState = bottomState,
            field = SheetField.UpKind,
            state = uiState,
            viewModel = petKindViewModel
        )
        DropDownTextField(
            labelText = stringResource(id = R.string.common_kinds),
            value = uiState?.selectedKind?.value?.knm ?: stringResource(id = R.string.common_all),
            bottomState = bottomState,
            field = SheetField.Kind,
            state = uiState,
            viewModel = petKindViewModel
        )
        TextButton(
            onClick = {
                parentViewModel?.requestPets(petKindViewModel!!.requestQuery)
                navigateBack.invoke()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.textButtonColors(
                contentColor = Color.White,
                backgroundColor = AbandonedPetsTheme.colors.primaryColor
            )) {
            Text(text = stringResource(id = R.string.common_confirm))
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun SelectListing(
    uiState: PetKindState,
    bottomState: ModalBottomSheetState,
    onSelectKind: (KindResultEntity, SheetField) -> Unit
) {

    when (uiState.screenState) {
        is ScreenState.Loading -> {
            ScreenLoading()
        }
        is ScreenState.Error -> {

        }
        is ScreenState.Success -> {
            val listState = rememberLazyListState()
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .background(color = Color.Transparent)
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                uiState.upKind?.let { kinds ->
                    items(kinds.kindEntities.size) { index ->
                        SheetItem(
                            uiState = uiState,
                            listState = listState,
                            bottomState = bottomState,
                            index = index,
                            kinds = kinds,
                            onSelectKind = onSelectKind,
                            filed = SheetField.UpKind
                        )
                    }
                }
                uiState.kind?.let { kinds ->
                    items(kinds.kindEntities.size) { index ->
                        SheetItem(
                            uiState = uiState,
                            listState = listState,
                            bottomState = bottomState,
                            index = index,
                            kinds = kinds,
                            onSelectKind = onSelectKind,
                            filed = SheetField.Kind
                        )
                    }
                }
            }
        }
    }
}



@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
private fun MainContentPreView() {
    AbandonedPetsTheme {
        MainContent(Modifier, null, null, null, null) { }
    }
}
