package wwon.seokk.abandonedpets.data.remote.datasource

import wwon.seokk.abandonedpets.data.remote.model.request.GetAbandonmentPublicRequest
import wwon.seokk.abandonedpets.data.remote.model.request.GetKindRequest
import wwon.seokk.abandonedpets.data.remote.model.request.GetShelterRequest
import wwon.seokk.abandonedpets.data.remote.model.request.GetSigunguRequest
import wwon.seokk.abandonedpets.data.remote.model.response.common.CommonResponse

/**
 * Created by WonSeok on 2022.08.02
 **/
interface AbandonedPetsDataSource {
    suspend fun getSido(): CommonResponse
    suspend fun getSigungu(getSigunguRequest: GetSigunguRequest): CommonResponse
    suspend fun getShelter(getShelterRequest: GetShelterRequest): CommonResponse
    suspend fun getKind(getKindRequest: GetKindRequest): CommonResponse
    suspend fun getAbandonmentPublic(getAbandonmentPublicRequest: GetAbandonmentPublicRequest): CommonResponse
}