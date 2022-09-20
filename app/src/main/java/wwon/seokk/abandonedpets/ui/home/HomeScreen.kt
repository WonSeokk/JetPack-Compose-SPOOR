package wwon.seokk.abandonedpets.ui.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch
import wwon.seokk.abandonedpets.ui.base.ScreenState
import wwon.seokk.abandonedpets.R
import wwon.seokk.abandonedpets.data.remote.ApiConstants
import wwon.seokk.abandonedpets.data.remote.model.request.GetAbandonmentPublicRequest
import wwon.seokk.abandonedpets.domain.entity.abandonmentpublic.AbandonmentPublicResultEntity
import wwon.seokk.abandonedpets.domain.interatctor.PetsSource
import wwon.seokk.abandonedpets.ui.common.*
import wwon.seokk.abandonedpets.ui.theme.AbandonedPetsTheme
import wwon.seokk.abandonedpets.util.rememberLazyListState
import wwon.seokk.abandonedpets.util.setStatusBar

/**
 * Created by WonSeok on 2022.08.05
 **/
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    widthSize: WindowWidthSizeClass,
    openPetRegionSearch: (GetAbandonmentPublicRequest) -> Unit,
    openPetKindSearch: (GetAbandonmentPublicRequest) -> Unit,
    openCalendar: (GetAbandonmentPublicRequest) -> Unit,
    openPetDetail: (AbandonmentPublicResultEntity) -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBar(true)

    val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Revealed)
    val lifecycleOwner = LocalLifecycleOwner.current
    val stateFlow = homeViewModel.uiState()
    val stateLifecycleAware = remember(lifecycleOwner, stateFlow) {
        stateFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val state by stateLifecycleAware.collectAsState(initial = homeViewModel.createInitialState())

    LaunchedEffect(homeViewModel.uiSideEffect()) {
        val messageHost = SnackBarView(this)
        homeViewModel.uiSideEffect().collect { uiSideEffect ->
            when (uiSideEffect) {
                is HomeSideEffect.ShowSnackBar -> {
                    messageHost.showSnackBar(
                        snackBarHostState = scaffoldState.snackbarHostState,
                        message = uiSideEffect.message
                    )
                }
            }
        }
    }

    BackdropScaffold(
        modifier = Modifier.statusBarsPadding(),
        scaffoldState = scaffoldState,
        backLayerBackgroundColor = AbandonedPetsTheme.colors.surfaceVariantColor,
        frontLayerShape = AbandonedPetsTheme.shapes.bottomSheetShape,
        frontLayerBackgroundColor = AbandonedPetsTheme.colors.surfaceColor,
        frontLayerScrimColor = Color.Unspecified,
        frontLayerElevation = 0.dp,
        appBar = {
            HomeAppBar()
        },
        backLayerContent = {
            PetSearchContent(widthSize, state, openPetRegionSearch, openPetKindSearch, openCalendar)
        },
        frontLayerContent = {
            HomeContent(scaffoldState = scaffoldState, homeViewModel = homeViewModel, uiState = state, openPetDetail = openPetDetail)
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeContent(
    scaffoldState: BackdropScaffoldState,
    homeViewModel: HomeViewModel,
    uiState: HomeState,
    openPetDetail: (AbandonmentPublicResultEntity) -> Unit
) {
    Column {
        HeaderScreen(
            scaffoldState = scaffoldState,
            modifier = Modifier
                .fillMaxWidth()
                .height(35.dp)
                .padding(start = 12.dp, top = 12.dp, end = 12.dp, bottom = 0.dp)
        )
        Spacer(Modifier.height(12.dp))
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 5.dp)
                .wrapContentSize(Alignment.Center),
            color = Color.White,
            shape = AbandonedPetsTheme.shapes.bottomSheetShape) {
            Column {
                PetListing(homeViewModel = homeViewModel, uiState = uiState, openPetDetail = openPetDetail)
            }
        }
    }

}


@Composable
fun PetListing(
    homeViewModel: HomeViewModel,
    uiState: HomeState,
    openPetDetail: (AbandonmentPublicResultEntity) -> Unit
) {
    val isLoading = remember { mutableStateOf(false)}

    when (uiState.screenState) {
        is ScreenState.Loading -> {
        }
        is ScreenState.Error -> {
            GetPetsError { homeViewModel.initData() }
        }
        is ScreenState.Success -> {
            val lazyPetItems = uiState.abandonedPets?.collectAsLazyPagingItems()
            lazyPetItems?.let { petItems ->
                val listState: LazyListState =
                    if(petItems.itemCount <= ApiConstants.NUM_ROW && isLoading.value)
                        rememberLazyListState()
                    else
                        petItems.rememberLazyListState()
                LazyColumn(state = listState) {
                    items(petItems.itemCount) { index ->
                        petItems[index]?.let { petInfo ->
                            PetCard(
                                pet = petInfo,
                                petClick = { openPetDetail.invoke(petInfo) } )
                            PetListDivider()
                        }
                    }
                    petItems.apply {
                        when {
                            loadState.refresh is LoadState.Loading -> {
                                item {  ScreenLoading() }
                            }
                            loadState.append is LoadState.Loading -> {
                                isLoading.value = true
                                item { ScreenLoading() }
                            }
                            loadState.refresh is LoadState.Error -> {
                                val msg = (loadState.refresh as LoadState.Error).error.message
                                if(msg == PetsSource.EMPTY_LIST)
                                    item { EmptyResult() }
                                else
                                    homeViewModel.handlePaginationDataError()
                            }
                            loadState.append is LoadState.Error -> {
                                val msg = (loadState.append as LoadState.Error).error.message
                                if(msg != PetsSource.EMPTY_LIST)
                                    homeViewModel.handlePaginationDataError()
                            }
                            loadState.append is LoadState.NotLoading -> {
                                isLoading.value = false
                                if(loadState.append.endOfPaginationReached && itemCount == 0) {
                                    homeViewModel.handlePaginationDataError()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HeaderScreen(scaffoldState: BackdropScaffoldState, modifier: Modifier) {
    Box(modifier = modifier) {
        val scope = rememberCoroutineScope()
        Text(
            text = stringResource(id = R.string.home_screen_header_text),
            style = AbandonedPetsTheme.typography.title1.copy(
                fontSize = 18.sp
            )
        )
        BackdropButton(
            isRevealed = scaffoldState.isRevealed,
            modifier = Modifier
                .align(Alignment.TopEnd)
        ) {
            scope.launch {
                if(scaffoldState.isRevealed) scaffoldState.conceal() else scaffoldState.reveal()
            }
        }
    }
}




//@Preview(showBackground = true)
//@Composable
//fun SearchTitlePreview() {
//    SearchScreen("전체","전체","전체","2022.07.01 ~ 2022.07.30")
//}

