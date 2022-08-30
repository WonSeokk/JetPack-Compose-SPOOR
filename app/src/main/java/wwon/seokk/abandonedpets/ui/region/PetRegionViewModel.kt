package wwon.seokk.abandonedpets.ui.region

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import wwon.seokk.abandonedpets.data.remote.model.request.GetAbandonmentPublicRequest
import wwon.seokk.abandonedpets.domain.entity.region.RegionEntity
import wwon.seokk.abandonedpets.domain.entity.region.RegionResultEntity
import wwon.seokk.abandonedpets.domain.entity.shelter.ShelterEntity
import wwon.seokk.abandonedpets.domain.entity.shelter.ShelterResultEntity
import wwon.seokk.abandonedpets.domain.interatctor.GetShelterUseCase
import wwon.seokk.abandonedpets.domain.interatctor.GetSidoUseCase
import wwon.seokk.abandonedpets.domain.interatctor.GetSigunguUseCase
import wwon.seokk.abandonedpets.ui.PetRequestArgs.PetRequest
import wwon.seokk.abandonedpets.ui.base.BaseViewModel
import wwon.seokk.abandonedpets.ui.base.ScreenState
import wwon.seokk.abandonedpets.ui.common.SheetField
import javax.inject.Inject

/**
 * Created by WonSeok on 2022.08.18
 **/
@HiltViewModel
class PetRegionViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getSidoUseCase: GetSidoUseCase,
    private val getSigunguUseCase: GetSigunguUseCase,
    private val getShelterUseCase: GetShelterUseCase
) : BaseViewModel<PetRegionState, PetRegionSideEffect>(savedStateHandle) {

    var requestQuery = GetAbandonmentPublicRequest.EMPTY

    override fun createInitialState(): PetRegionState = PetRegionState(
        ScreenState.Loading,
        null,
        null,
        null,
        mutableStateOf(RegionResultEntity.BASE),
        mutableStateOf(RegionResultEntity.BASE),
        mutableStateOf(ShelterResultEntity.BASE),
        null
    )

    override fun initData() {
        requestQuery = savedStateHandle[PetRequest]!!
        uiState().value.run {
            selectedUprRegion.value = requestQuery.upr
            selectedOrgRegion.value = requestQuery.org
            selectedShelter.value = requestQuery.shelter
        }
    }

    private fun resetOrg() {
        uiState().value.selectedOrgRegion.value = RegionResultEntity.BASE
        requestQuery.org = RegionResultEntity.BASE
    }

    private fun resetShelter() {
        uiState().value.selectedShelter.value = ShelterResultEntity.BASE
        requestQuery.shelter = ShelterResultEntity.BASE
    }


    fun selectRegion(query: RegionResultEntity? = null, field: SheetField) = intent {
        query?.let {
            if(field == SheetField.UprRegion) {
                resetOrg()
                resetShelter()
                requestQuery.upr = it
            }
            else if(field == SheetField.OrgRegion) {
                resetShelter()
                requestQuery.org = it
            }
        }
    }

    fun selectShelter(query: ShelterResultEntity? = null) = intent {
        query?.let { requestQuery.shelter = it }
    }

    private fun loadingState() = intent  {
        reduce { state.copy(screenState = ScreenState.Loading ) }
    }

    fun getSido() = intent {
        loadingState()
        getSidoUseCase.invoke(null).collect { record ->
            if (record.data != null) {
                reduce {
                    val data = mutableListOf(RegionResultEntity.BASE)
                    data.addAll(record.data.regionEntities)
                    state.copy(
                        screenState = ScreenState.Success,
                        uprRegions = RegionEntity(data),
                        orgRegions = null,
                        shelters = null,
                        error = null
                    )
                }
            } else {
                reduce {
                    state.copy(
                        screenState = ScreenState.Error,
                        uprRegions = null,
                        orgRegions = null,
                        shelters = null,
                        error = record.error
                    )
                }
                postSideEffect(PetRegionSideEffect.ShowRegionErrorToast)
            }
        }
    }

    fun getSigungu() = intent {
        loadingState()
        uiState().value.selectedUprRegion.value.orgCd.let { orgCd ->
            if(orgCd.isBlank())
                return@intent

            val data = mutableListOf(RegionResultEntity.BASE)
            val request = GetSigunguUseCase.RequestValue(orgCd)
            getSigunguUseCase.invoke(request).collect { record ->
                if (record.data != null) {
                    reduce {
                        if(record.data.regionEntities.isNotEmpty())
                            data.addAll(record.data.regionEntities)
                        state.copy(
                            screenState = ScreenState.Success,
                            uprRegions = null,
                            orgRegions = RegionEntity(data),
                            shelters = null,
                            error = null
                        )
                    }
                } else {
                    reduce {
                        state.copy(
                            screenState = ScreenState.Error,
                            uprRegions = null,
                            orgRegions = null,
                            shelters = null,
                            error = record.error
                        )
                    }
                    postSideEffect(PetRegionSideEffect.ShowRegionErrorToast)
                }
            }
        }
    }

    fun getShelter() = intent {
        loadingState()
        uiState().value.let { state ->
            val selectedUpr = state.selectedUprRegion.value
            val selectedOrg = state.selectedOrgRegion.value
            if(selectedOrg.orgCd.isBlank())
                return@intent

            val data = mutableListOf(ShelterResultEntity.BASE)
            val request = GetShelterUseCase.RequestValue(selectedUpr.orgCd, selectedOrg.orgCd)
            getShelterUseCase.invoke(request).collect { record ->
                if (record.data != null) {
                    reduce {
                        if(record.data.shelterEntities.isNotEmpty())
                            data.addAll(record.data.shelterEntities)
                        state.copy(
                            screenState = ScreenState.Success,
                            uprRegions = null,
                            orgRegions = null,
                            shelters = ShelterEntity(data),
                            error = null
                        )
                    }
                } else {
                    reduce {
                        state.copy(
                            screenState = ScreenState.Error,
                            uprRegions = null,
                            orgRegions = null,
                            shelters = null,
                            error = record.error
                        )
                    }
                    postSideEffect(PetRegionSideEffect.ShowRegionErrorToast)
                }
            }
        }
    }

}