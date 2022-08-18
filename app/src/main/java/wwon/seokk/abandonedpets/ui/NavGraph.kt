package wwon.seokk.abandonedpets.ui

import androidx.navigation.NavHostController
import wwon.seokk.abandonedpets.ui.Destinations.PetKind
import wwon.seokk.abandonedpets.ui.Destinations.PetRegion

/**
 * Created by WonSeok on 2022.08.02
 **/
object Destinations {
    const val Home = "home"
    const val PetRegion = "petRegion"
    const val PetKind = "petKind"
}

class Actions(navHostController: NavHostController) {
    val openPetRegionSearch: () -> Unit = {
        navHostController.navigate(PetRegion)
    }

    val openPetKindSearch: () -> Unit = {
        navHostController.navigate(PetKind)
    }

    val navigateBack: () -> Unit = {
        navHostController.navigateUp()
    }
}