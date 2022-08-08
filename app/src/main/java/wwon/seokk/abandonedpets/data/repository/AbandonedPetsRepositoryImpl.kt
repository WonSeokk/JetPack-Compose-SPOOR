package wwon.seokk.abandonedpets.data.repository

import wwon.seokk.abandonedpets.data.remote.RemoteException
import wwon.seokk.abandonedpets.data.remote.datasource.AbandonedPetsDataSource
import wwon.seokk.abandonedpets.data.remote.mapper.AbandonedPetsMapper
import wwon.seokk.abandonedpets.data.remote.mapper.ErrorMapper
import wwon.seokk.abandonedpets.data.remote.model.request.GetAbandonmentPublicRequest
import wwon.seokk.abandonedpets.data.remote.model.request.GetKindRequest
import wwon.seokk.abandonedpets.data.remote.model.request.GetShelterRequest
import wwon.seokk.abandonedpets.data.remote.model.request.GetSigunguRequest
import wwon.seokk.abandonedpets.domain.entity.abandonmentpublic.AbandonmentPublicEntity
import wwon.seokk.abandonedpets.domain.entity.location.LocationEntity
import wwon.seokk.abandonedpets.domain.entity.base.Record
import wwon.seokk.abandonedpets.domain.entity.kind.KindEntity
import wwon.seokk.abandonedpets.domain.entity.shelter.ShelterEntity
import wwon.seokk.abandonedpets.domain.repository.AbandonedPetsRepository
import javax.inject.Inject

/**
 * Created by WonSeok on 2022.08.02
 **/
class AbandonedPetsRepositoryImpl @Inject constructor(private val abandonedPetsDataSource: AbandonedPetsDataSource): AbandonedPetsRepository {
    private val abandonedPetsMapper = AbandonedPetsMapper()
    private val errorMapper = ErrorMapper()

    override suspend fun getSido(): Record<LocationEntity> {
        return try {
            abandonedPetsDataSource.getSido().run {
                abandonedPetsMapper.mapLocationResponse(this)
            }
        } catch (e: RemoteException) {
            errorMapper.mapErrorRecord(e)
        }
    }

    override suspend fun getSigungu(uprCd: String): Record<LocationEntity> {
        return try {
            abandonedPetsDataSource.getSigungu(GetSigunguRequest(uprCd)).run {
                abandonedPetsMapper.mapLocationResponse(this)
            }
        } catch (e: RemoteException) {
            errorMapper.mapErrorRecord(e)
        }
    }

    override suspend fun getShelter(uprCd: String, orgCd: String): Record<ShelterEntity> {
        return try {
            abandonedPetsDataSource.getShelter(GetShelterRequest(uprCd, orgCd)).run {
                abandonedPetsMapper.mapShelterResponse(this)
            }
        } catch (e: RemoteException) {
            errorMapper.mapErrorRecord(e)
        }
    }

    override suspend fun getKind(kindCd: String): Record<KindEntity> {
        return try {
            abandonedPetsDataSource.getKind(GetKindRequest(kindCd)).run {
                abandonedPetsMapper.mapKindResponse(this)
            }
        } catch (e: RemoteException) {
            errorMapper.mapErrorRecord(e)
        }
    }

    override suspend fun getAbandonmentPublic(getAbandonmentPublicRequest: GetAbandonmentPublicRequest): Record<AbandonmentPublicEntity> {
        return try {
            abandonedPetsDataSource.getAbandonmentPublic(getAbandonmentPublicRequest.copy()).run {
                 abandonedPetsMapper.mapAbandonmentPublicResponse(this)
            }
        } catch (e: RemoteException) {
            errorMapper.mapErrorRecord(e)
        }
    }

}