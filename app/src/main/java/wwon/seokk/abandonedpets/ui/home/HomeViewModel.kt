package wwon.seokk.abandonedpets.ui.home

import androidx.lifecycle.SavedStateHandle
import androidx.paging.Pager
import androidx.paging.PagingConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import wwon.seokk.abandonedpets.data.remote.ApiConstants
import wwon.seokk.abandonedpets.data.remote.model.request.GetAbandonmentPublicRequest
import wwon.seokk.abandonedpets.domain.entity.abandonmentpublic.AbandonmentPublicResultEntity
import wwon.seokk.abandonedpets.domain.interatctor.PetsSource
import wwon.seokk.abandonedpets.domain.repository.AbandonedPetsRepository
import wwon.seokk.abandonedpets.ui.base.BaseViewModel
import wwon.seokk.abandonedpets.ui.base.ScreenState
import javax.inject.Inject

/**
 * Created by WonSeok on 2022.08.05
 **/
@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val abandonedPetsRepository: AbandonedPetsRepository
) : BaseViewModel<HomeState, HomeSideEffect>(savedStateHandle) {

    private val requestQueryFlow: MutableStateFlow<GetAbandonmentPublicRequest> = MutableStateFlow(GetAbandonmentPublicRequest.EMPTY)

    override fun createInitialState(): HomeState = HomeState(ScreenState.Loading, null, null)

    override fun initData() = intent {
        val result = getPets()
        reduce {
            state.copy(screenState = ScreenState.Success, abandonedPets = result.flow, error = null)
        }
    }

    private fun getPets(): Pager<Int, AbandonmentPublicResultEntity> {
        return Pager(PagingConfig(ApiConstants.NUM_ROW)) {
            PetsSource(abandonedPetsRepository, requestQueryFlow.value)
        }
    }
}