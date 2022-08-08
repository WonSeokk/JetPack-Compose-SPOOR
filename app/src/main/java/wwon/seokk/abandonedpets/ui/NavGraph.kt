package wwon.seokk.abandonedpets.ui

import androidx.navigation.NavHostController

/**
 * Created by WonSeok on 2022.08.02
 **/
object Destinations {
    const val Home = "home"
}

class Actions(navHostController: NavHostController) {
    val navigateBack: () -> Unit = {
        navHostController.navigateUp()
    }
}