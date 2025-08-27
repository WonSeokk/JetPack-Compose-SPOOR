package wwon.seokk.abandonedpets.ui.details

import androidx.annotation.StringRes
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import wwon.seokk.abandonedpets.domain.entity.abandonmentpublic.AbandonmentPublicResultEntity
import wwon.seokk.abandonedpets.domain.interatctor.GetLikedPetsUseCase
import wwon.seokk.abandonedpets.ui.PetRequestArgs
import wwon.seokk.abandonedpets.ui.base.BaseViewModel
import javax.inject.Inject

/**
 * Created by WonSeok on 2022.08.31
 **/
@HiltViewModel
class PetDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getLikedPetsUseCase: GetLikedPetsUseCase
) : BaseViewModel<PetDetailsState, PetDetailSideEffect>(savedStateHandle) {

    override fun createInitialState(): PetDetailsState = PetDetailsState()

    override fun initData() = intent {
        val petDetail: AbandonmentPublicResultEntity = savedStateHandle[PetRequestArgs.PetInfo]!!
        uiState().value.petDetail.value = petDetail
        getFavorites()
    }

    fun getFavorites() = intent {
        getLikedPetsUseCase.invoke(null).collect { pets ->
            reduce {
                state.copy(favorites = pets.map { it.desertionNo })
            }
        }
    }

    fun handleSnackBar(@StringRes message: Int, action: String) = intent {
        postSideEffect(PetDetailSideEffect.ShowSnackBar(message = message, action = action))
    }

}
