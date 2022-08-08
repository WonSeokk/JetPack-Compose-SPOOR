package wwon.seokk.abandonedpets.ui.home

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import wwon.seokk.abandonedpets.domain.entity.abandonmentpublic.AbandonmentPublicResultEntity
import wwon.seokk.abandonedpets.domain.entity.base.ErrorRecord
import wwon.seokk.abandonedpets.ui.base.ScreenState

/**
 * Created by WonSeok on 2022.08.05
 **/
data class HomeState(
    val screenState: ScreenState,
    val abandonedPets: Flow<PagingData<AbandonmentPublicResultEntity>>?,
    val error: ErrorRecord?
)