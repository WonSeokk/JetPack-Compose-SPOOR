package wwon.seokk.abandonedpets.ui.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import wwon.seokk.abandonedpets.R
import wwon.seokk.abandonedpets.data.local.entities.Pet
import wwon.seokk.abandonedpets.data.local.entities.toPublicResult
import wwon.seokk.abandonedpets.domain.entity.abandonmentpublic.AbandonmentPublicResultEntity
import wwon.seokk.abandonedpets.ui.common.EmptyResult
import wwon.seokk.abandonedpets.ui.common.NavigateUpAppBar
import wwon.seokk.abandonedpets.ui.common.PetListDivider
import wwon.seokk.abandonedpets.ui.common.SnackBarView
import wwon.seokk.abandonedpets.ui.home.HomeViewModel
import wwon.seokk.abandonedpets.ui.home.PetCard
import wwon.seokk.abandonedpets.ui.theme.AbandonedPetsTheme
import wwon.seokk.abandonedpets.util.setStatusBar

/**
 * Created by WonSeok on 2022.11.07
 **/
@Composable
fun FavoriteScreen(
    darkTheme: Boolean,
    parentViewModel: HomeViewModel,
    favoriteViewModel: FavoriteViewModel = hiltViewModel(),
    openPetDetail: (AbandonmentPublicResultEntity) -> Unit,
    navigateBack: () -> Unit
) {
    val favoriteClick: (AbandonmentPublicResultEntity, MutableState<Boolean>) -> Unit = { pet, state ->
        parentViewModel.handleLikePet(pet, state, null, favoriteViewModel)
    }
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBar(darkTheme.not())
    systemUiController.setSystemBarsColor(AbandonedPetsTheme.colors.surfaceColor)


    val lifecycleOwner = LocalLifecycleOwner.current
    val scaffoldState = rememberScaffoldState()
    val stateFlow = favoriteViewModel.uiState()
    val stateLifecycleAware = remember(lifecycleOwner, stateFlow) {
        stateFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val uiState by stateLifecycleAware.collectAsState(initial = favoriteViewModel.createInitialState())

    val context = LocalContext.current
    LaunchedEffect(favoriteViewModel.uiSideEffect()) {
        val messageHost = SnackBarView(this)
        favoriteViewModel.uiSideEffect().collect { uiSideEffect ->
            when (uiSideEffect) {
                is FavoriteSideEffect.ShowSnackBar -> {
                    messageHost.showSnackBar(
                        snackBarHostState = scaffoldState.snackbarHostState,
                        message = context.getString(uiSideEffect.message)
                    )
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        topBar = {
            NavigateUpAppBar(title = stringResource(id = R.string.screen_favorite), navigateBack = navigateBack)
        },
        content = { FavoriteContent(uiState.favorites.reversed(), favoriteClick, openPetDetail) }
    )
}

@Composable
fun FavoriteContent(
    favorites: List<Pet>,
    favoriteClick: (AbandonmentPublicResultEntity, MutableState<Boolean>) -> Unit,
    openPetDetail: (AbandonmentPublicResultEntity) -> Unit
) {
    Column(Modifier.background(color = AbandonedPetsTheme.colors.surfaceColor)) {
        Surface(
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .padding(start = 15.dp, top = 10.dp, end = 15.dp, bottom = 5.dp),
            shape = AbandonedPetsTheme.shapes.mediumRoundCornerShape,
            color = AbandonedPetsTheme.colors.subColor
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Text(
                    text = stringResource(id = R.string.favorite_screen_end_notice),
                    style = AbandonedPetsTheme.typography.body1.copy(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = AbandonedPetsTheme.colors.surfaceOppositeColor
                    ),
                    modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 5.dp)
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(if (favorites.isEmpty()) Alignment.Center else Alignment.TopStart)
        ) {
            if(favorites.isEmpty()) item { EmptyResult(R.string.favorite_screen_empty_message) }

            items(favorites.count()) { index ->
                favorites[index].let { petInfo ->
                    PetCard(
                        pet = petInfo.toPublicResult(),
                        isLiked = true,
                        favoriteClick = favoriteClick,
                        petClick = openPetDetail
                    )
                    PetListDivider()
                }
            }
        }

    }

}