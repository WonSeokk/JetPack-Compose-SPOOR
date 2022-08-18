package wwon.seokk.abandonedpets.ui.region

import wwon.seokk.abandonedpets.domain.entity.base.ErrorRecord
import wwon.seokk.abandonedpets.domain.entity.region.RegionEntity
import wwon.seokk.abandonedpets.ui.base.ScreenState

/**
 * Created by WonSeok on 2022.08.18
 **/

data class PetRegionState (
    val screenState: ScreenState,
    val regions: RegionEntity?,
    val error: ErrorRecord?
)