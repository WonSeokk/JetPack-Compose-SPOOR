package wwon.seokk.abandonedpets.ui.kind

import wwon.seokk.abandonedpets.ui.region.PetRegionSideEffect

/**
 * Created by WonSeok on 2022.08.23
 **/
sealed class PetKindSideEffect {
    data class ShowSnackBar(val message: String): PetKindSideEffect()
    object ShowNetworkError: PetKindSideEffect()
}