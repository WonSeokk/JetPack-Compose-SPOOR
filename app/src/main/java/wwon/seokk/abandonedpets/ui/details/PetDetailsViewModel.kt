package wwon.seokk.abandonedpets.ui.details

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
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
) : BaseViewModel<PetDetailsState, PetSideEffect>(savedStateHandle) {

    override fun createInitialState(): PetDetailsState = PetDetailsState()

    override fun initData() {
        val petDetail: AbandonmentPublicResultEntity = savedStateHandle[PetRequestArgs.PetInfo]!!
        uiState().value.petDetail.value = petDetail
    }

}