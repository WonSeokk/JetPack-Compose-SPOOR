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
        return commonResponse.response.body.items.run {
            if(item != null)
                Record(RegionEntity(item.toRegionsEntity()), null)
            else
                Record(RegionEntity(emptyList()), null)
        }

    }

    fun mapShelterResponse(commonResponse: CommonResponse): Record<ShelterEntity> {
        return commonResponse.response.body.items.run {
            if(item != null)
                Record(ShelterEntity(item.toSheltersEntity()), null)
            else
                Record(ShelterEntity(emptyList()), null)

        }
    }

    fun mapKindResponse(commonResponse: CommonResponse): Record<KindEntity> {
        return commonResponse.response.body.items.run {
            if(item != null)
                Record(KindEntity(item.toKindsEntity()), null)
            else
                Record(KindEntity(emptyList()), null)
        }
    }

    fun mapAbandonmentPublicResponse(commonResponse: CommonResponse): Record<AbandonmentPublicEntity> {
        return commonResponse.response.body.items.run {
            if(item != null)
                Record(AbandonmentPublicEntity(item.toAbandonmentPublicEntities()), null)
            else
                Record(AbandonmentPublicEntity(emptyList()), null)
        }
    }
}