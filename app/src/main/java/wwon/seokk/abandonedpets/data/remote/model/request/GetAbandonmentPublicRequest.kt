package wwon.seokk.abandonedpets.data.remote.model.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import wwon.seokk.abandonedpets.data.remote.ApiConstants
import wwon.seokk.abandonedpets.domain.entity.kind.KindResultEntity
import wwon.seokk.abandonedpets.domain.entity.region.RegionResultEntity
import wwon.seokk.abandonedpets.domain.entity.shelter.ShelterResultEntity

/**
 * Created by WonSeok on 2022.08.02
 **/
@Parcelize
data class GetAbandonmentPublicRequest (
    val startDate: String,
    val endDate: String,
    var upKind: KindResultEntity,
    var kind: KindResultEntity,
    var upr: RegionResultEntity,
    var org: RegionResultEntity,
    var shelter: ShelterResultEntity,
    var nextPage: Int,
    val numRow: Int = ApiConstants.NUM_ROW
): Parcelable {
    companion object {
        val EMPTY = GetAbandonmentPublicRequest("", "",  KindResultEntity.BASE, KindResultEntity.BASE, RegionResultEntity.BASE, RegionResultEntity.BASE, ShelterResultEntity.BASE, 1)
    }
}