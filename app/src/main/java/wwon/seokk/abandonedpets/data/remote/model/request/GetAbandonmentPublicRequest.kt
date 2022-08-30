package wwon.seokk.abandonedpets.data.remote.model.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import wwon.seokk.abandonedpets.data.remote.ApiConstants
import wwon.seokk.abandonedpets.domain.entity.kind.KindResultEntity
import wwon.seokk.abandonedpets.domain.entity.region.RegionResultEntity
import wwon.seokk.abandonedpets.domain.entity.shelter.ShelterResultEntity
import wwon.seokk.abandonedpets.util.toFormat
import java.time.LocalDate

/**
 * Created by WonSeok on 2022.08.02
 **/
@Parcelize
data class GetAbandonmentPublicRequest (
    var startDate: String = LocalDate.now().minusDays(7).toFormat(),
    var endDate: String = LocalDate.now().toFormat(),
    var upKind: KindResultEntity = KindResultEntity.BASE,
    var kind: KindResultEntity = KindResultEntity.BASE,
    var upr: RegionResultEntity = RegionResultEntity.BASE,
    var org: RegionResultEntity = RegionResultEntity.BASE,
    var shelter: ShelterResultEntity = ShelterResultEntity.BASE,
    var nextPage: Int = 1,
    val numRow: Int = ApiConstants.NUM_ROW
): Parcelable {
    companion object {
        val EMPTY = GetAbandonmentPublicRequest()
    }
}