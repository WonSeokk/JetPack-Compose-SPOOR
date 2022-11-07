package wwon.seokk.abandonedpets.ui.favorite

import androidx.annotation.StringRes

/**
 * Created by WonSeok on 2022.11.07
 **/
sealed class FavoriteSideEffect {
    data class ShowSnackBar(@StringRes val message: Int, val action: String): FavoriteSideEffect()
}