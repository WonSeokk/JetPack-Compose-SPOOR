package wwon.seokk.abandonedpets.data.remote.datasource

import wwon.seokk.abandonedpets.data.remote.abandonedpets.AbandonedPetsService
import wwon.seokk.abandonedpets.data.remote.model.request.GetAbandonmentPublicRequest
import wwon.seokk.abandonedpets.data.remote.model.request.GetKindRequest
import wwon.seokk.abandonedpets.data.remote.model.request.GetShelterRequest
import wwon.seokk.abandonedpets.data.remote.model.request.GetSigunguRequest
import wwon.seokk.abandonedpets.data.remote.model.response.common.CommonResponse
import javax.inject.Inject
import wwon.seokk.abandonedpets.data.Result
import wwon.seokk.abandonedpets.data.remote.RemoteException

/**
 * Created by WonSeok on 2022.08.02
 **/
class AbandonedPetsDataSourceImpl @Inject constructor(
    private val abandonedPetsService: AbandonedPetsService
): AbandonedPetsDataSource {

    override suspend fun getSido(): Result<CommonResponse> {
        return try{
            Result.Success(abandonedPetsService.getSido())
        } catch (ex: Exception) {
            Result.Error(RemoteException.ServerError(ex.message.toString()))
        }
    }

    override suspend fun getSigungu(getSigunguRequest: GetSigunguRequest): Result<CommonResponse> {
        return try{
            Result.Success(abandonedPetsService.getSigungu(getSigunguRequest.uprCd))
        } catch (ex: Exception) {
            Result.Error(RemoteException.ServerError(ex.message.toString()))
        }
    }

    override suspend fun getShelter(getShelterRequest: GetShelterRequest): Result<CommonResponse> {
        return try{
            Result.Success(abandonedPetsService.getShelter(getShelterRequest.uprCd, getShelterRequest.orgCd))
        } catch (ex: Exception) {
            Result.Error(RemoteException.ServerError(ex.message.toString()))
        }
    }

    override suspend fun getKind(getKindRequest: GetKindRequest): Result<CommonResponse> {
        return try{
            Result.Success(abandonedPetsService.getKind(getKindRequest.kindCd))
        } catch (ex: Exception) {
            Result.Error(RemoteException.ServerError(ex.message.toString()))
        }
    }

    override suspend fun getAbandonmentPublic(getAbandonmentPublicRequest: GetAbandonmentPublicRequest): Result<CommonResponse> {
        return try{
            getAbandonmentPublicRequest.run {
                Result.Success(abandonedPetsService.getAbandonmentPublic(startDate, endDate, upKind.kindCd, kind.kindCd, upr.orgCd, org.orgCd, shelter.careRegNo, nextPage, numRow))
            }
        } catch (ex: Exception) {
            Result.Error(RemoteException.ServerError(ex.message.toString()))
        }

    }

}