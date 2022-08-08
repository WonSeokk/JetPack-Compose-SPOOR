package wwon.seokk.abandonedpets.data.remote.model.request

import wwon.seokk.abandonedpets.data.remote.ApiConstants

/**
 * Created by WonSeok on 2022.08.02
 **/
data class GetAbandonmentPublicRequest (
    val startDate: String,
    val endDate: String,
    val upkind: String,
    val kind: String,
    val uprCd: String,
    val orgCd: String,
    val careRegNo: String,
    var nextPage: Int,
    val numRow: Int = ApiConstants.NUM_ROW
) {
    companion object {
        val EMPTY = GetAbandonmentPublicRequest("", "", "", "", "", "", "", 1)
    }
}