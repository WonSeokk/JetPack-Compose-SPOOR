package wwon.seokk.abandonedpets.ui.region

import androidx.compose.runtime.MutableState
import wwon.seokk.abandonedpets.domain.entity.base.ErrorRecord
import wwon.seokk.abandonedpets.domain.entity.region.RegionEntity
import wwon.seokk.abandonedpets.domain.entity.region.RegionResultEntity
import wwon.seokk.abandonedpets.domain.entity.shelter.ShelterEntity
import wwon.seokk.abandonedpets.domain.entity.shelter.ShelterResultEntity
import wwon.seokk.abandonedpets.ui.base.ScreenState

/**
 * Created by WonSeok on 2022.08.18
 **/

data class PetRegionState (
    val screenState: ScreenState,
    val uprRegions: RegionEntity?,
    val orgRegions: RegionEntity?,
    val shelters: ShelterEntity?,
    val selectedUprRegion: MutableState<RegionResultEntity>,
    val selectedOrgRegion: MutableState<RegionResultEntity>,
    val selectedShelter: MutableState<ShelterResultEntity>,
    val error: ErrorRecord?
)