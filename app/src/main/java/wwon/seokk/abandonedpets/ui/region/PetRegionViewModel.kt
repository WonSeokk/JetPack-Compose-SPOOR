package wwon.seokk.abandonedpets.ui.region

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import wwon.seokk.abandonedpets.domain.interatctor.GetSidoUseCase
import wwon.seokk.abandonedpets.domain.interatctor.GetSigunguUseCase
import wwon.seokk.abandonedpets.ui.base.BaseViewModel
import wwon.seokk.abandonedpets.ui.base.ScreenState
import javax.inject.Inject

/**
 * Created by WonSeok on 2022.08.18
 **/
@HiltViewModel
class PetRegionViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getSidoUseCase: GetSidoUseCase,
    private val getSigunguUseCase: GetSigunguUseCase
) : BaseViewModel<PetRegionState, PetRegionSideEffect>(savedStateHandle) {

    override fun createInitialState(): PetRegionState = PetRegionState(ScreenState.Loading, null, null)

    override fun initData()  = intent {
        getSidoUseCase.invoke(null).collect { record ->
            if (record.data != null) {
                reduce {
                    state.copy(
                        screenState = ScreenState.Success,
                        regions = record.data,
                        error = null
                    )
                }
            } else {
                reduce {
                    state.copy(
                        screenState = ScreenState.Error,
                        regions = null,
                        error = record.error
                    )
                }
                postSideEffect(PetRegionSideEffect.ShowRegionErrorToast)
            }
        }
    }

}