package wwon.seokk.abandonedpets.ui.kind

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import wwon.seokk.abandonedpets.data.remote.abandonedpets.upKinds
import wwon.seokk.abandonedpets.data.remote.model.request.GetAbandonmentPublicRequest
import wwon.seokk.abandonedpets.domain.entity.kind.KindEntity
import wwon.seokk.abandonedpets.domain.entity.kind.KindResultEntity
import wwon.seokk.abandonedpets.domain.entity.region.RegionEntity
import wwon.seokk.abandonedpets.domain.entity.region.RegionResultEntity
import wwon.seokk.abandonedpets.domain.entity.shelter.ShelterResultEntity
import wwon.seokk.abandonedpets.domain.interatctor.GetKindUseCase
import wwon.seokk.abandonedpets.domain.interatctor.GetSigunguUseCase
import wwon.seokk.abandonedpets.ui.PetRequestArgs
import wwon.seokk.abandonedpets.ui.base.BaseViewModel
import wwon.seokk.abandonedpets.ui.base.ScreenState
import wwon.seokk.abandonedpets.ui.common.SheetField
import wwon.seokk.abandonedpets.ui.home.HomeSideEffect
import wwon.seokk.abandonedpets.ui.region.PetRegionSideEffect
import javax.inject.Inject

/**
 * Created by WonSeok on 2022.08.23
 **/
@HiltViewModel
class PetKindViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getKindUseCase: GetKindUseCase
) : BaseViewModel<PetKindState, PetKindSideEffect>(savedStateHandle) {

    var requestQuery = GetAbandonmentPublicRequest.EMPTY

    override fun createInitialState(): PetKindState = PetKindState(
        ScreenState.Loading,
        null,
        null,
        mutableStateOf(KindResultEntity.BASE),
        mutableStateOf(KindResultEntity.BASE),
        null
    )

    override fun initData() {
        requestQuery = savedStateHandle[PetRequestArgs.PetRequest]!!
        uiState().value.selectedUpKind.value = requestQuery.upKind
        uiState().value.selectedKind.value = requestQuery.kind
    }

    private fun resetKind() {
        uiState().value.selectedKind.value = KindResultEntity.BASE
        requestQuery.kind = KindResultEntity.BASE
    }

    fun selectKind(query: KindResultEntity? = null, field: SheetField) = intent {
        query?.let {
            if(field == SheetField.UpKind) {
                resetKind()
                requestQuery.upKind = it
            }
            else if(field == SheetField.Kind)
                requestQuery.kind = it
        }
    }

    private fun loadingState() = intent  {
        reduce { state.copy(screenState = ScreenState.Loading ) }
    }

    fun getUpKind() = intent {
        reduce {
            val data = mutableListOf(KindResultEntity.BASE).let {
                it.addAll(upKinds.kindEntities)
                it
            }
            state.copy(
                screenState = ScreenState.Success,
                upKind = KindEntity(data),
                kind = null,
                error = null
            )
        }
    }

    fun getKind() = intent {
        loadingState()
        uiState().value.selectedUpKind.value.kindCd.let { kindCd ->
            if(kindCd.isBlank())
                return@intent

            val data = mutableListOf(KindResultEntity.BASE)
            val request = GetKindUseCase.RequestValue(kindCd)
            getKindUseCase.invoke(request).collect { record ->
                if (record.data != null) {
                    reduce {
                        if(record.data.kindEntities.isNotEmpty())
                            data.addAll(record.data.kindEntities)
                        state.copy(
                            screenState = ScreenState.Success,
                            upKind = null,
                            kind = KindEntity(data),
                            error = null
                        )
                    }
                } else {
                    reduce {
                        state.copy(
                            screenState = ScreenState.Error,
                            upKind = null,
                            kind = null,
                            error = record.error
                        )
                    }
                    postSideEffect(PetKindSideEffect.ShowNetworkError)
                }
            }
        }
    }

    fun handleSnackBar(message: String) = intent {
        postSideEffect(PetKindSideEffect.ShowSnackBar(message = message))
    }
}