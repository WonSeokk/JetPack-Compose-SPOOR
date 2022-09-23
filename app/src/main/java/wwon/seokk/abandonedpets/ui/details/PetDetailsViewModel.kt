package wwon.seokk.abandonedpets.ui.details

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import wwon.seokk.abandonedpets.domain.entity.abandonmentpublic.AbandonmentPublicResultEntity
import wwon.seokk.abandonedpets.ui.PetRequestArgs
import wwon.seokk.abandonedpets.ui.base.BaseViewModel
import javax.inject.Inject

/**
 * Created by WonSeok on 2022.08.31
 **/
@HiltViewModel
class PetDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel<PetDetailsState, PetDetailSideEffect>(savedStateHandle) {

    override fun createInitialState(): PetDetailsState = PetDetailsState()

    override fun initData() {
        val petDetail: AbandonmentPublicResultEntity = savedStateHandle[PetRequestArgs.PetInfo]!!
        uiState().value.petDetail.value = petDetail
    }

    fun handleSnackBar(message: String, action: String) = intent {
        postSideEffect(PetDetailSideEffect.ShowSnackBar(message = message, action = action))
    }

}