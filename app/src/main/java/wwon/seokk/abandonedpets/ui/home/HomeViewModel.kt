package wwon.seokk.abandonedpets.ui.home

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import wwon.seokk.abandonedpets.data.remote.ApiConstants
import wwon.seokk.abandonedpets.data.remote.model.request.GetAbandonmentPublicRequest
import wwon.seokk.abandonedpets.domain.entity.abandonmentpublic.AbandonmentPublicResultEntity
import wwon.seokk.abandonedpets.domain.entity.base.ErrorRecord
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

    private val requestQueryFlow: MutableSharedFlow<GetAbandonmentPublicRequest> = MutableSharedFlow()

    override fun createInitialState(): HomeState = HomeState(ScreenState.Loading, mutableStateOf(GetAbandonmentPublicRequest.EMPTY), null, null)

    override fun initData() {
        handleSearch()
        requestPets(uiState().value.requestQuery.value)
    }

    private fun handleSearch() = intent {
        requestQueryFlow.collect {
            val result = getPets()
            reduce {
                state.copy(
                    screenState = ScreenState.Success,
                    abandonedPets = result.flow.cachedIn(viewModelScope),
                    error = null
                )
            }
        }
    }

    fun requestPets(query: GetAbandonmentPublicRequest = GetAbandonmentPublicRequest.EMPTY) = intent {
        uiState().value.requestQuery.value = query
        requestQueryFlow.emit(query)
    }

    private fun getPets(): Pager<Int, AbandonmentPublicResultEntity> {
        return Pager(PagingConfig(ApiConstants.NUM_ROW)) {
            PetsSource(abandonedPetsRepository, uiState().value.requestQuery.value)
        }
    }

    fun handlePaginationDataError() = intent {
        reduce {
            state.copy(screenState = ScreenState.Error,
                abandonedPets = null,
                error = ErrorRecord.ServerError
            )
        }
    }
}