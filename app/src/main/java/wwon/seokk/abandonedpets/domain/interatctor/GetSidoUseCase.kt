package wwon.seokk.abandonedpets.domain.interatctor

import wwon.seokk.abandonedpets.domain.BaseUseCase
import wwon.seokk.abandonedpets.domain.entity.base.Record
import wwon.seokk.abandonedpets.domain.entity.region.RegionEntity
import wwon.seokk.abandonedpets.domain.repository.AbandonedPetsRepository
import javax.inject.Inject

/**
 * Created by WonSeok on 2022.08.18
 **/
class GetSidoUseCase @Inject constructor(private val abandonedPetsRepository: AbandonedPetsRepository,) :
    BaseUseCase<Nothing?, Record<RegionEntity>>() {

    override suspend fun run(request: Nothing?): Record<RegionEntity> {
        return abandonedPetsRepository.getSido()
    }
}