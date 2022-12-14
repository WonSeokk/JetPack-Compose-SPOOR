package wwon.seokk.abandonedpets.ui.region

/**
 * Created by WonSeok on 2022.08.18
 **/

sealed class PetRegionSideEffect {
    data class ShowSnackBar(val message: String): PetRegionSideEffect()
    object ShowNetworkError: PetRegionSideEffect()
}