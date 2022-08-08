package wwon.seokk.abandonedpets.data.remote.mapper

import wwon.seokk.abandonedpets.data.remote.model.response.common.CommonResponse
import wwon.seokk.abandonedpets.data.remote.model.response.toAbandonmentPublicsEntity
import wwon.seokk.abandonedpets.data.remote.model.response.toKindsEntity
import wwon.seokk.abandonedpets.data.remote.model.response.toLocationsEntity
import wwon.seokk.abandonedpets.data.remote.model.response.toSheltersEntity
import wwon.seokk.abandonedpets.domain.entity.abandonmentpublic.AbandonmentPublicEntity
import wwon.seokk.abandonedpets.domain.entity.location.LocationEntity
import wwon.seokk.abandonedpets.domain.entity.base.Record
import wwon.seokk.abandonedpets.domain.entity.kind.KindEntity
import wwon.seokk.abandonedpets.domain.entity.shelter.ShelterEntity

/**
 * Created by WonSeok on 2022.08.02
 **/
class AbandonedPetsMapper {

    fun mapLocationResponse(commonResponse: CommonResponse): Record<LocationEntity> {
        return Record(LocationEntity(commonResponse.response.body.items.item.toLocationsEntity()), null)
    }

    fun mapShelterResponse(commonResponse: CommonResponse): Record<ShelterEntity> {
        return Record(ShelterEntity(commonResponse.response.body.items.item.toSheltersEntity()), null)
    }

    fun mapKindResponse(commonResponse: CommonResponse): Record<KindEntity> {
        return Record(KindEntity(commonResponse.response.body.items.item.toKindsEntity()), null)
    }

    fun mapAbandonmentPublicResponse(commonResponse: CommonResponse): Record<AbandonmentPublicEntity> {
        return Record(AbandonmentPublicEntity(commonResponse.response.body.items.item.toAbandonmentPublicsEntity()), null)
    }
}