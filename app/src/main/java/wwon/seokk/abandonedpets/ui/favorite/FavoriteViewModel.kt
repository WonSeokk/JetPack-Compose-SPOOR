package wwon.seokk.abandonedpets.ui.favorite

import androidx.annotation.StringRes
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import wwon.seokk.abandonedpets.domain.interatctor.GetLikedPetsUseCase
import wwon.seokk.abandonedpets.ui.base.BaseViewModel
import javax.inject.Inject

/**
 * Created by WonSeok on 2022.11.07
 **/
@HiltViewModel
class FavoriteViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getLikedPetsUseCase: GetLikedPetsUseCase
) : BaseViewModel<FavoriteState, FavoriteSideEffect>(savedStateHandle) {

    override fun createInitialState(): FavoriteState = FavoriteState()

    override fun initData() = intent {
        getFavorites()
    }

    fun getFavorites() = intent {
        getLikedPetsUseCase.invoke(null).collect { pets ->
            reduce {
                state.copy(favorites = pets)
            }
        }
    }

    fun handleSnackBar(@StringRes message: Int, action: String) = intent {
        postSideEffect(FavoriteSideEffect.ShowSnackBar(message = message, action = action))
    }
}
