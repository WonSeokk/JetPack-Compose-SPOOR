package wwon.seokk.abandonedpets.ui

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import wwon.seokk.abandonedpets.ui.Destinations.Home
import wwon.seokk.abandonedpets.ui.Destinations.PetKind
import wwon.seokk.abandonedpets.ui.Destinations.PetRegion
import wwon.seokk.abandonedpets.ui.PetRequestArgs.PetRequest
import wwon.seokk.abandonedpets.ui.home.HomeScreen
import wwon.seokk.abandonedpets.ui.home.HomeViewModel
import wwon.seokk.abandonedpets.ui.kind.PetKindScreen
import wwon.seokk.abandonedpets.ui.kind.PetKindViewModel
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
                openPetKindSearch = actions.openPetKindSearch
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
    }
}