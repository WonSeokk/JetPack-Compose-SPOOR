package wwon.seokk.abandonedpets.domain.entity.region

import android.os.Parcelable
import androidx.compose.runtime.Stable
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable

/**
 * Created by WonSeok on 2022.08.02
 **/
@Parcelize
data class RegionResultEntity(
    val uprCd: String?,
    val orgCd: String,
    val orgNm: String
): Parcelable {
    companion object {
        val BASE = RegionResultEntity("", "", "전체")
    }
}