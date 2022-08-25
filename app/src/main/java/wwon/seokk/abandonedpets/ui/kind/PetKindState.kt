package wwon.seokk.abandonedpets.ui.kind

import androidx.compose.runtime.MutableState
import wwon.seokk.abandonedpets.domain.entity.base.ErrorRecord
import wwon.seokk.abandonedpets.domain.entity.kind.KindEntity
import wwon.seokk.abandonedpets.domain.entity.kind.KindResultEntity
import wwon.seokk.abandonedpets.ui.base.ScreenState

/**
 * Created by WonSeok on 2022.08.23
 **/
data class PetKindState (
    val screenState: ScreenState,
    val upKind: KindEntity?,
    val kind: KindEntity?,
    val selectedUpKind: MutableState<KindResultEntity>,
    val selectedKind: MutableState<KindResultEntity>,
    val error: ErrorRecord?
)