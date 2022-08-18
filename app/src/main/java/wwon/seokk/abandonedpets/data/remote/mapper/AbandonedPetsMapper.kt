package wwon.seokk.abandonedpets.data.remote.mapper

import wwon.seokk.abandonedpets.data.remote.model.response.*
import wwon.seokk.abandonedpets.data.remote.model.response.common.CommonResponse
import wwon.seokk.abandonedpets.domain.entity.abandonmentpublic.AbandonmentPublicEntity
import wwon.seokk.abandonedpets.domain.entity.region.RegionEntity
import wwon.seokk.abandonedpets.domain.entity.base.Record
import wwon.seokk.abandonedpets.domain.entity.kind.KindEntity
import wwon.seokk.abandonedpets.domain.entity.shelter.ShelterEntity

/**
 * Created by WonSeok on 2022.08.02
 **/
class AbandonedPetsMapper {

    fun mapRegionResponse(commonResponse: CommonResponse): Record<RegionEntity> {
        return Record(RegionEntity(commonResponse.response.body.items.item.toRegionsEntity()), null)
    }

    fun mapShelterResponse(commonResponse: CommonResponse): Record<ShelterEntity> {
        return Record(ShelterEntity(commonResponse.response.body.items.item.toSheltersEntity()), null)
    }

    fun mapKindResponse(commonResponse: CommonResponse): Record<KindEntity> {
        return Record(KindEntity(commonResponse.response.body.items.item.toKindsEntity()), null)
    }

    fun mapAbandonmentPublicResponse(commonResponse: CommonResponse): Record<AbandonmentPublicEntity> {
        return Record(AbandonmentPublicEntity(commonResponse.response.body.items.item.toAbandonmentPublicEntities()), null)
    }
}