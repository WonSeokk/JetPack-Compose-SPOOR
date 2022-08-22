package wwon.seokk.abandonedpets.ui

import android.net.Uri
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.google.gson.Gson
import wwon.seokk.abandonedpets.data.remote.model.request.GetAbandonmentPublicRequest
import wwon.seokk.abandonedpets.ui.Destinations.Home
import wwon.seokk.abandonedpets.ui.Destinations.PetKind
import wwon.seokk.abandonedpets.ui.Destinations.PetRegion
import wwon.seokk.abandonedpets.ui.PetRequestArgs.PetRequest

/**
 * Created by WonSeok on 2022.08.02
 **/
object Destinations {
    const val Home = "home"
    const val PetRegion = "petRegion"
    const val PetKind = "petKind"
}

object PetRequestArgs {
    const val PetRequest = "petRequest"
}

class Actions(navHostController: NavHostController) {
    val openPetRegionSearch: (GetAbandonmentPublicRequest) -> Unit = {
        val request = Uri.encode(Gson().toJson(it))
        navHostController.navigate("$PetRegion/$request")
    }

    val openPetKindSearch: () -> Unit = {
        navHostController.navigate(PetKind)
    }

    val backHomeWithArg: (GetAbandonmentPublicRequest) -> Unit = {
        val request = Uri.encode(Gson().toJson(it))
        val homeRoute = navHostController.graph.startDestinationRoute
        navHostController.popBackStack(homeRoute!!, false)
    }

    val navigateBack: () -> Unit = {
        navHostController.popBackStack()
    }
}