package wwon.seokk.abandonedpets.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import wwon.seokk.abandonedpets.ui.base.ScreenState
import wwon.seokk.abandonedpets.ui.common.SnackBarView
import wwon.seokk.abandonedpets.R
import wwon.seokk.abandonedpets.domain.entity.abandonmentpublic.AbandonmentPublicResultEntity

/**
 * Created by WonSeok on 2022.08.05
 **/
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()

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

    Scaffold(topBar = {
//        HomeAppBar(
//            title = stringResource(id = R.string.home_app_bar_title),
//            searchClick = { openSearch.invoke() },
//            filterClick = {  }
//        )
    },
        scaffoldState = scaffoldState,
        content = { paddingValues ->  PetListing(paddingValues = paddingValues, homeViewModel = homeViewModel) }
    )
}

@Composable
fun PetListing(paddingValues: PaddingValues, homeViewModel: HomeViewModel) {
    val errorMessage: String = stringResource(id = R.string.home_screen_scroll_error)
    val action: String = stringResource(id = R.string.all_ok)
    val lifecycleOwner = LocalLifecycleOwner.current
    val stateFlow = homeViewModel.uiState()
    val stateLifecycleAware = remember(lifecycleOwner, stateFlow) {
        stateFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val state by stateLifecycleAware.collectAsState(initial = homeViewModel.createInitialState())

    when (state.screenState) {
        is ScreenState.Loading -> {
            //do nothing
        }
        is ScreenState.Error -> {
//            GetGamesError { homeViewModel.initData() }
        }
        is ScreenState.Success -> {
            val lazyPetItems = state.abandonedPets?.collectAsLazyPagingItems()
            lazyPetItems?.let { petItems ->
                LazyColumn {
                    items(petItems.itemCount) { index ->
                        petItems[index]?.let {
                            PetItem(pet = it, petClick = {} )
                            PetListDivider()
                        }
                    }
                    petItems.apply {
                        when {
                            loadState.refresh is LoadState.Loading -> {
                                item { FullScreenLoading() }
                            }
                            loadState.append is LoadState.Loading -> {
                                item { FullScreenLoading() }
                            }
                            loadState.refresh is LoadState.Error -> {
//                                homeViewModel.handlePaginationDataError()
                            }
                            loadState.append is LoadState.Error -> {
//                                homeViewModel.handlePaginationAppendError(errorMessage, action)
                            }
                        }
                    }

                }
            }
        }
    }
}

@Composable
private fun PetListDivider() {
    Divider(
        modifier = Modifier.padding(horizontal = 14.dp),
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
    )
}

@Composable
private fun FullScreenLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun SearchScreen(
    sido: String,
    sigungu: String,
    shelter: String,
    date: String
) {
    Row(
        modifier = Modifier.height(32.dp)
    ) {
        Column(
            Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)) {
            Text(
                text = "$sido | $sigungu | $shelter | $date",
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .padding(start = 8.dp)
            )
        }
        Image(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun PetItem(
    pet: AbandonmentPublicResultEntity,
    petClick: (AbandonmentPublicResultEntity) -> Unit
) {
    Card(
        elevation = 0.dp,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
            .clickable(
                enabled = true,
                onClick = {
                    petClick(pet)
                })
    ) {
        Row {
            Column(
                Modifier.weight(1f)
            ) {
                Row {
                    Text(text = pet.processState,
                        color = if(pet.processState == "보호중") Color(0xFF8BC34A) else Color(0xFFF44336),
                        modifier = Modifier.padding(end = 8.dp))
                    Text(text = "2022.08.01 ~ 2022.08.11")
                }
                Text(text = "${pet.kindCd} | ${pet.colorCd} | ${pet.age}"
                )
                Text(text = pet.careNm,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(top = 8.dp)
                )
                Text(text = pet.happenPlace)
            }
            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .width(120.dp)
                    .height(120.dp)
                    .clip(RoundedCornerShape(10.dp))
            ) {
                AsyncImage(model = ImageRequest.Builder(LocalContext.current)
                    .data(pet.popfile)
                    .crossfade(true)
                    .build(),
                    contentScale = ContentScale.FillBounds,
                    placeholder = painterResource(R.drawable.ic_pets),
                    contentDescription = stringResource(id = R.string.pet_image_description),
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchTitlePreview() {
    SearchScreen("전체","전체","전체","2022.07.01 ~ 2022.07.30")
}


@Preview(showBackground = true)
@Composable
fun PetItemPreview() {
    PetItem(
        AbandonmentPublicResultEntity("","","","",
            "","","","","","","","","보호중",
            "","","","","","","","",""),
        petClick ={ }
    )
}