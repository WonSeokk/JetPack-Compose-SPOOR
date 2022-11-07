package wwon.seokk.abandonedpets.ui.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import wwon.seokk.abandonedpets.domain.entity.abandonmentpublic.AbandonmentPublicResultEntity

/**
 * Created by WonSeok on 2022.08.31
 **/
data class PetDetailsState (
    val petDetail: MutableState<AbandonmentPublicResultEntity> = mutableStateOf(AbandonmentPublicResultEntity.EMPTY),
    val favorites: List<String> = emptyList()
)