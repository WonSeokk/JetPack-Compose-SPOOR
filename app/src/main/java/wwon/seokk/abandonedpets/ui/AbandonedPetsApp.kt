package wwon.seokk.abandonedpets.ui

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import wwon.seokk.abandonedpets.ui.Destinations.Calendar
import wwon.seokk.abandonedpets.ui.Destinations.Home
import wwon.seokk.abandonedpets.ui.Destinations.Image
import wwon.seokk.abandonedpets.ui.Destinations.PetDetail
import wwon.seokk.abandonedpets.ui.Destinations.PetKind
import wwon.seokk.abandonedpets.ui.Destinations.PetRegion
import wwon.seokk.abandonedpets.ui.PetRequestArgs.ImageUri
import wwon.seokk.abandonedpets.ui.PetRequestArgs.PetInfo
import wwon.seokk.abandonedpets.ui.PetRequestArgs.PetRequest
import wwon.seokk.abandonedpets.ui.calendar.CalendarScreen
import wwon.seokk.abandonedpets.ui.details.ImagePreview
import wwon.seokk.abandonedpets.ui.details.PetDetailsScreen
import wwon.seokk.abandonedpets.ui.home.HomeScreen
import wwon.seokk.abandonedpets.ui.home.HomeViewModel
import wwon.seokk.abandonedpets.ui.kind.PetKindScreen
import wwon.seokk.abandonedpets.ui.region.PetRegionScreen

/**
 * Created by WonSeok on 2022.08.02
 **/
@Composable
fun AbandonedPetsApp(widthSize: WindowWidthSizeClass) {
    val navController = rememberNavController()
    val actions = remember(navController) { Actions(navController) }
    NavHost(navController = navController, startDestination = Home) {
        composable(Home) {
            HomeScreen(
                widthSize = widthSize,
                openPetRegionSearch = actions.openPetRegionSearch,
                openPetKindSearch = actions.openPetKindSearch,
                openCalendar = actions.openCalendar,
                openPetDetail = actions.openPetDetail
            )
        }
        composable("$PetRegion?petRequest={$PetRequest}",
            arguments = listOf(
                navArgument(PetRequest) {
                    type = AbandonmentPublicRequestNavType()
                }
            )) {
            val parentEntry = remember { navController.getBackStackEntry(Home)  }
            val parentViewModel = hiltViewModel<HomeViewModel>(parentEntry)
            PetRegionScreen(
                parentViewModel = parentViewModel,
                navigateBack = actions.navigateBack
            )
        }
        composable("$PetKind?petRequest={$PetRequest}",
            arguments = listOf(
                navArgument(PetRequest) {
                    type = AbandonmentPublicRequestNavType()
                }
            )) {
            val parentEntry = remember { navController.getBackStackEntry(Home)  }
            val parentViewModel = hiltViewModel<HomeViewModel>(parentEntry)
            PetKindScreen(
                parentViewModel = parentViewModel,
                navigateBack = actions.navigateBack
            )
        }
        composable("$Calendar?petRequest={$PetRequest}",
            arguments = listOf(
                navArgument(PetRequest) {
                    type = AbandonmentPublicRequestNavType()
                }
            )) {
            val parentEntry = remember { navController.getBackStackEntry(Home)  }
            val parentViewModel = hiltViewModel<HomeViewModel>(parentEntry)
            CalendarScreen(
                parentViewModel = parentViewModel,
                navigateBack = actions.navigateBack
            )
        }

        composable("$PetDetail?petInfo={$PetInfo}",
            arguments = listOf(
                navArgument(PetInfo) {
                    type = AbandonmentPublicResultNavType()
                }
            )) {
            PetDetailsScreen(
                openImage = actions.openImagePerView,
                navigateBack = actions.navigateBack
            )
        }

        composable("$Image?uri={$ImageUri}",
            arguments = listOf(
                navArgument(ImageUri) {
                    type = NavType.StringType
                }
            )) {
            val uri = it.arguments?.getString(ImageUri)!!
            ImagePreview(
                uri = uri,
                navigateBack = actions.navigateBack
            )
        }
    }
}