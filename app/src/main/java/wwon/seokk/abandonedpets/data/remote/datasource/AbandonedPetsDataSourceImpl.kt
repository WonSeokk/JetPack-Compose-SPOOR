package wwon.seokk.abandonedpets.data.remote.datasource

import wwon.seokk.abandonedpets.data.remote.abandonedpets.AbandonedPetsService
import wwon.seokk.abandonedpets.data.remote.model.request.GetAbandonmentPublicRequest
import wwon.seokk.abandonedpets.data.remote.model.request.GetKindRequest
import wwon.seokk.abandonedpets.data.remote.model.request.GetShelterRequest
import wwon.seokk.abandonedpets.data.remote.model.request.GetSigunguRequest
import wwon.seokk.abandonedpets.data.remote.model.response.common.CommonResponse
import javax.inject.Inject

/**
 * Created by WonSeok on 2022.08.02
 **/
class AbandonedPetsDataSourceImpl @Inject constructor(
    private val abandonedPetsService: AbandonedPetsService
): AbandonedPetsDataSource {

    override suspend fun getSido(): CommonResponse {
        return abandonedPetsService.getSido()
    }

    override suspend fun getSigungu(getSigunguRequest: GetSigunguRequest): CommonResponse {
        return abandonedPetsService.getSigungu(getSigunguRequest.uprCd)
    }

    override suspend fun getShelter(getShelterRequest: GetShelterRequest): CommonResponse {
        return abandonedPetsService.getShelter(getShelterRequest.uprCd, getShelterRequest.orgCd)
    }

    override suspend fun getKind(getKindRequest: GetKindRequest): CommonResponse {
        return abandonedPetsService.getKind(getKindRequest.kindCd)
    }

    override suspend fun getAbandonmentPublic(getAbandonmentPublicRequest: GetAbandonmentPublicRequest): CommonResponse {
        return getAbandonmentPublicRequest.run {
            abandonedPetsService.getAbandonmentPublic(startDate, endDate, upKind.kindCd, kind.kindCd, upr.orgCd, org.orgCd, shelter.careRegNo, nextPage, numRow)
        }
    }

}