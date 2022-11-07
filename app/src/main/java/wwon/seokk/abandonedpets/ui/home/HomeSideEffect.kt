package wwon.seokk.abandonedpets.ui.home

import androidx.annotation.StringRes

/**
 * Created by WonSeok on 2022.08.05
 **/
sealed class HomeSideEffect {
    data class ShowSnackBar(@StringRes val message: Int, val action: String): HomeSideEffect()
}