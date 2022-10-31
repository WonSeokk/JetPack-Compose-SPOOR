package wwon.seokk.abandonedpets.ui.region

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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import wwon.seokk.abandonedpets.R
import wwon.seokk.abandonedpets.domain.entity.region.RegionResultEntity
import wwon.seokk.abandonedpets.domain.entity.shelter.ShelterResultEntity
import wwon.seokk.abandonedpets.ui.base.ScreenState
import wwon.seokk.abandonedpets.ui.common.*
import wwon.seokk.abandonedpets.ui.home.HomeViewModel
import wwon.seokk.abandonedpets.ui.theme.AbandonedPetsTheme

/**
 * Created by WonSeok on 2022.08.18
 **/

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PetRegionScreen(
    parentViewModel: HomeViewModel,
    petRegionViewModel: PetRegionViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    fun onSelectRegion(query: RegionResultEntity, field: SheetField) {
        petRegionViewModel.selectRegion(query, field)
    }

    fun onSelectShelter(query: ShelterResultEntity) {
        petRegionViewModel.selectShelter(query)
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    val stateFlow = petRegionViewModel.uiState()
    val stateLifecycleAware = remember(lifecycleOwner, stateFlow) {
        stateFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val state by stateLifecycleAware.collectAsState(initial = petRegionViewModel.createInitialState())

    val scaffoldState = rememberScaffoldState()
    val bottomState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden, skipHalfExpanded = true)

    val errorMsg = stringResource(id = R.string.common_network_error_message)

    LaunchedEffect(petRegionViewModel.uiSideEffect()) {
        val messageHost = SnackBarView(this)
        petRegionViewModel.uiSideEffect().collect { uiSideEffect ->
            when (uiSideEffect) {
                is PetRegionSideEffect.ShowSnackBar -> {
                    messageHost.showSnackBar(
                        snackBarHostState = scaffoldState.snackbarHostState,
                        message = uiSideEffect.message
                    )
                }
                is PetRegionSideEffect.ShowNetworkError -> {
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
        sheetBackgroundColor = Color.White,
        sheetContent = {
            BottomContent(
                uiState = state,
                bottomState = bottomState,
                onSelectRegion = { query, field -> onSelectRegion(query, field) },
                onSelectShelter = { query -> onSelectShelter(query) }
             )
        }
    ) {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                NavigateUpAppBar(navigateBack = navigateBack)
            },
            content = {
                MainContent(
                    uiState = state,
                    bottomState = bottomState,
                    parentViewModel = parentViewModel,
                    petRegionViewModel = petRegionViewModel,
                    navigateBack = navigateBack
                )
            }
        )
    }


}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun MainContent(
    uiState: PetRegionState?,
    bottomState: ModalBottomSheetState?,
    parentViewModel: HomeViewModel?,
    petRegionViewModel: PetRegionViewModel?,
    navigateBack: () -> Unit
) {
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxSize()
    ) {
        NoticeTitle(contentText = stringResource(id = R.string.region_screen_questions_message))
        DropDownTextField(
            labelText = stringResource(id = R.string.region_screen_sido),
            value = uiState?.selectedUprRegion?.value?.orgNm ?: stringResource(id = R.string.common_all),
            bottomState = bottomState,
            field = SheetField.UprRegion,
            state = uiState,
            viewModel = petRegionViewModel
        )
        DropDownTextField(labelText = stringResource(id = R.string.region_screen_sigungu),
            value = uiState?.selectedOrgRegion?.value?.orgNm ?: stringResource(id = R.string.common_all),
            bottomState = bottomState,
            field = SheetField.OrgRegion,
            state = uiState,
            viewModel = petRegionViewModel
        )
        DropDownTextField(
            labelText = stringResource(id = R.string.region_screen_shelter),
            value = uiState?.selectedShelter?.value?.careNm ?: stringResource(id = R.string.common_all),
            bottomState = bottomState,
            field = SheetField.Shelter,
            state = uiState,
            viewModel = petRegionViewModel
        )
        TextButton(
            onClick = {
                parentViewModel?.requestPets(petRegionViewModel!!.requestQuery)
                scope.launch { delay(500) }
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
private fun BottomContent(
    uiState: PetRegionState,
    bottomState: ModalBottomSheetState,
    onSelectRegion: (RegionResultEntity, SheetField) -> Unit,
    onSelectShelter: (ShelterResultEntity) -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
    ) {
        Column {
            NoticeTitle(contentText = stringResource(id = R.string.region_screen_select_message))
            SelectListing(uiState, bottomState, onSelectRegion, onSelectShelter)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun SelectListing(
    uiState: PetRegionState,
    bottomState: ModalBottomSheetState,
    onSelectRegion: (RegionResultEntity, SheetField) -> Unit,
    onSelectShelter: (ShelterResultEntity) -> Unit
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
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                uiState.uprRegions?.let { regions ->
                    items(regions.regionEntities.size) { index ->
                        SheetItem(
                            uiState = uiState,
                            listState = listState,
                            bottomState = bottomState,
                            index = index,
                            regions = regions,
                            onSelectRegion = onSelectRegion,
                            filed = SheetField.UprRegion
                        )
                    }
                }
                uiState.orgRegions?.let { regions ->
                    items(regions.regionEntities.size) { index ->
                        SheetItem(
                            uiState = uiState,
                            listState = listState,
                            bottomState = bottomState,
                            index = index,
                            regions = regions,
                            onSelectRegion = onSelectRegion,
                            filed = SheetField.OrgRegion
                        )
                    }
                }

                uiState.shelters?.let { shelters ->
                    items(shelters.shelterEntities.size) { index ->
                        SheetItem(
                            uiState = uiState,
                            listState = listState,
                            bottomState = bottomState,
                            index = index,
                            shelters = shelters,
                            onSelectShelter = onSelectShelter
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
        MainContent(null, null, null,null) { }
    }
}
