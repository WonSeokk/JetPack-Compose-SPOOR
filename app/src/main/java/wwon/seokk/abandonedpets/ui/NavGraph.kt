package wwon.seokk.abandonedpets.ui

import android.net.Uri
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.google.gson.Gson
import wwon.seokk.abandonedpets.data.remote.model.request.GetAbandonmentPublicRequest
import wwon.seokk.abandonedpets.domain.entity.abandonmentpublic.AbandonmentPublicResultEntity
import wwon.seokk.abandonedpets.ui.Destinations.Calendar
import wwon.seokk.abandonedpets.ui.Destinations.Home
import wwon.seokk.abandonedpets.ui.Destinations.Image
import wwon.seokk.abandonedpets.ui.Destinations.PetDetail
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
    const val Calendar = "calendar"
    const val PetDetail = "petDetail"
    const val Image = "image"
}

object PetRequestArgs {
    const val PetRequest = "petRequest"
    const val PetInfo = "petInfo"
    const val ImageUri = "imageUri"
}

class Actions(navHostController: NavHostController) {
    val openPetRegionSearch: (GetAbandonmentPublicRequest) -> Unit = {
        val request = Uri.encode(Gson().toJson(it))
        navHostController.navigate("$PetRegion?petRequest=$request")
    }

    val openPetKindSearch: (GetAbandonmentPublicRequest) -> Unit = {
        val request = Uri.encode(Gson().toJson(it))
        navHostController.navigate("$PetKind?petRequest=$request")
    }

    val openCalendar: (GetAbandonmentPublicRequest) -> Unit = {
        val request = Uri.encode(Gson().toJson(it))
        navHostController.navigate("$Calendar?petRequest=$request")
    }

    val openPetDetail: (AbandonmentPublicResultEntity) -> Unit = {
        val request = Uri.encode(Gson().toJson(it))
        navHostController.navigate("$PetDetail?petInfo=$request")
    }

    val openImagePerView: (String) -> Unit = {
        navHostController.navigate("$Image?uri=$it")
    }

    val navigateBack: () -> Unit = {
        navHostController.navigateUp()
    }
}