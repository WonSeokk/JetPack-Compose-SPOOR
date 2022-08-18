package wwon.seokk.abandonedpets.domain.interatctor

import wwon.seokk.abandonedpets.domain.BaseUseCase
import wwon.seokk.abandonedpets.domain.entity.base.Record
import wwon.seokk.abandonedpets.domain.entity.region.RegionEntity
import wwon.seokk.abandonedpets.domain.repository.AbandonedPetsRepository
import javax.inject.Inject

/**
 * Created by WonSeok on 2022.08.18
 **/
class GetSigunguUseCase @Inject constructor(private val abandonedPetsRepository: AbandonedPetsRepository,) :
    BaseUseCase<GetSigunguUseCase.RequestValue, Record<RegionEntity>>() {

    override suspend fun run(request: RequestValue): Record<RegionEntity> {
        return abandonedPetsRepository.getSigungu(request.sido)
    }

    data class RequestValue(val sido: String)
}