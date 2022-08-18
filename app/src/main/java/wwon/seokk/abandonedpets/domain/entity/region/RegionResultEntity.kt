package wwon.seokk.abandonedpets.domain.entity.region

import androidx.compose.runtime.Stable
import javax.annotation.concurrent.Immutable

/**
 * Created by WonSeok on 2022.08.02
 **/
data class RegionResultEntity(
    val uprCd: String?,
    val orgCd: String,
    val orgNm: String
)