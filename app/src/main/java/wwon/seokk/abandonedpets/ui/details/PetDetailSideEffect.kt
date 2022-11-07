package wwon.seokk.abandonedpets.ui.details

import androidx.annotation.StringRes

/**
 * Created by WonSeok on 2022.08.31
 **/
sealed class PetDetailSideEffect {
    data class ShowSnackBar(@StringRes val message: Int, val action: String): PetDetailSideEffect()
}