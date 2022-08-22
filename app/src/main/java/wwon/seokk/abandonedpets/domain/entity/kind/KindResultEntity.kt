package wwon.seokk.abandonedpets.domain.entity.kind

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import wwon.seokk.abandonedpets.domain.entity.region.RegionResultEntity

/**
 * Created by WonSeok on 2022.08.02
 **/
@Parcelize
data class KindResultEntity (
    val kindCd: String,
    val knm: String,
): Parcelable {
    companion object {
        val BASE = KindResultEntity("", "전체")
    }
}