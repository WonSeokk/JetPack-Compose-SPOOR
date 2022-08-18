package wwon.seokk.abandonedpets.domain.repository

import wwon.seokk.abandonedpets.data.remote.model.request.GetAbandonmentPublicRequest
import wwon.seokk.abandonedpets.domain.entity.abandonmentpublic.AbandonmentPublicEntity
import wwon.seokk.abandonedpets.domain.entity.region.RegionEntity
import wwon.seokk.abandonedpets.domain.entity.base.Record
import wwon.seokk.abandonedpets.domain.entity.kind.KindEntity
import wwon.seokk.abandonedpets.domain.entity.shelter.ShelterEntity

/**
 * Created by WonSeok on 2022.08.02
 **/
interface AbandonedPetsRepository {
    suspend fun getSido(): Record<RegionEntity>
    suspend fun getSigungu(uprCd: String): Record<RegionEntity>
    suspend fun getShelter(uprCd: String, orgCd: String): Record<ShelterEntity>
    suspend fun getKind(kindCd: String): Record<KindEntity>
    suspend fun getAbandonmentPublic(getAbandonmentPublicRequest: GetAbandonmentPublicRequest): Record<AbandonmentPublicEntity>
}