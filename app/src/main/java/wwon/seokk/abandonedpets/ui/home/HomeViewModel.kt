package wwon.seokk.abandonedpets.ui.home

import androidx.annotation.StringRes
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import wwon.seokk.abandonedpets.R
import wwon.seokk.abandonedpets.data.remote.ApiConstants
import wwon.seokk.abandonedpets.data.remote.model.request.GetAbandonmentPublicRequest
import wwon.seokk.abandonedpets.domain.entity.abandonmentpublic.AbandonmentPublicResultEntity
import wwon.seokk.abandonedpets.domain.entity.abandonmentpublic.toPet
import wwon.seokk.abandonedpets.domain.entity.base.ErrorRecord
import wwon.seokk.abandonedpets.domain.interatctor.GetLikedPetsUseCase
import wwon.seokk.abandonedpets.domain.interatctor.LikePetUseCase
import wwon.seokk.abandonedpets.domain.interatctor.PetsSource
import wwon.seokk.abandonedpets.domain.repository.AbandonedPetsRepository
import wwon.seokk.abandonedpets.ui.base.BaseViewModel
import wwon.seokk.abandonedpets.ui.base.ScreenState
import wwon.seokk.abandonedpets.ui.details.PetDetailsViewModel
import wwon.seokk.abandonedpets.ui.favorite.FavoriteViewModel
import javax.inject.Inject

/**
 * Created by WonSeok on 2022.08.05
 **/
@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val abandonedPetsRepository: AbandonedPetsRepository,
    private val getLikedPetsUseCase: GetLikedPetsUseCase,
    private val likePetUseCase: LikePetUseCase
) : BaseViewModel<HomeState, HomeSideEffect>(savedStateHandle) {

    private val requestQueryFlow: MutableSharedFlow<GetAbandonmentPublicRequest> = MutableSharedFlow()

    override fun createInitialState(): HomeState = HomeState(ScreenState.Loading, mutableStateOf(GetAbandonmentPublicRequest.EMPTY), null, emptyList(), null)

    override fun initData() {
        handleSearch()
        requestPets(uiState().value.requestQuery.value)
        getFavorites()
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

    private fun getFavorites() = intent {
        getLikedPetsUseCase.invoke(null).collect { pets ->
            reduce {
                state.copy(favorites = pets.map { it.desertionNo })
            }
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

    fun handleLikePet(
        pet: AbandonmentPublicResultEntity,
        state: MutableState<Boolean>,
        detail: PetDetailsViewModel? = null,
        favorite: FavoriteViewModel? = null
    ) {
        viewModelScope.launch {
            val request = LikePetUseCase.RequestValue(pet.toPet(), state.value.not())
            likePetUseCase.invoke(request).collect{
                when(it) {
                    true -> {
                        if(state.value.not())
                            detail?.handleSnackBar(R.string.common_add_favorite_message,"") ?:
                            favorite?.handleSnackBar(R.string.common_add_favorite_message,"") ?:
                            handleSnackBar(R.string.common_add_favorite_message,"")
                        state.value = state.value.not()
                    }
                    else -> {
                        detail?.handleSnackBar(R.string.common_wait_message,"") ?:
                        favorite?.handleSnackBar(R.string.common_wait_message,"") ?:
                        handleSnackBar(R.string.common_wait_message,"")
                    }
                }
            }
            detail?.getFavorites()
            favorite?.getFavorites()
            getFavorites()
        }
    }

    private fun handleSnackBar(@StringRes message: Int, action: String) = intent {
        postSideEffect(HomeSideEffect.ShowSnackBar(message = message, action = action))
    }
}