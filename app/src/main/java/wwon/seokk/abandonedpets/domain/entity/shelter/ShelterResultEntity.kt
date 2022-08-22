package wwon.seokk.abandonedpets.domain.entity.shelter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import wwon.seokk.abandonedpets.domain.entity.region.RegionResultEntity

/**
 * Created by WonSeok on 2022.08.02
 **/
@Parcelize
data class ShelterResultEntity (
    val careRegNo: String,
    val careNm: String
): Parcelable {
    companion object {
        val BASE = ShelterResultEntity("","전체")
    }
}