package wwon.seokk.abandonedpets.data.remote.datasource

import wwon.seokk.abandonedpets.data.remote.model.request.GetAbandonmentPublicRequest
import wwon.seokk.abandonedpets.data.remote.model.request.GetKindRequest
import wwon.seokk.abandonedpets.data.remote.model.request.GetShelterRequest
import wwon.seokk.abandonedpets.data.remote.model.request.GetSigunguRequest
import wwon.seokk.abandonedpets.data.remote.model.response.common.CommonResponse
import wwon.seokk.abandonedpets.data.Result

/**
 * Created by WonSeok on 2022.08.02
 **/
interface AbandonedPetsDataSource {
    suspend fun getSido(): Result<CommonResponse>
    suspend fun getSigungu(getSigunguRequest: GetSigunguRequest): Result<CommonResponse>
    suspend fun getShelter(getShelterRequest: GetShelterRequest): Result<CommonResponse>
    suspend fun getKind(getKindRequest: GetKindRequest): Result<CommonResponse>
    suspend fun getAbandonmentPublic(getAbandonmentPublicRequest: GetAbandonmentPublicRequest): Result<CommonResponse>
}