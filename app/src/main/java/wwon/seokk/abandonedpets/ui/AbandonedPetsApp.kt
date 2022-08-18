package wwon.seokk.abandonedpets.ui

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import wwon.seokk.abandonedpets.ui.Destinations.Home
import wwon.seokk.abandonedpets.ui.Destinations.PetRegion
import wwon.seokk.abandonedpets.ui.home.HomeScreen
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
                openPetRegionSearch = actions.openPetRegionSearch
            )
        }
        composable(PetRegion) {
            PetRegionScreen(
                navigateBack = actions.navigateBack
            )
        }
    }
}