package wwon.seokk.abandonedpets.domain.interatctor

import wwon.seokk.abandonedpets.domain.BaseUseCase
import wwon.seokk.abandonedpets.domain.entity.base.Record
import wwon.seokk.abandonedpets.domain.entity.kind.KindEntity
import wwon.seokk.abandonedpets.domain.entity.region.RegionEntity
import wwon.seokk.abandonedpets.domain.repository.AbandonedPetsRepository
import javax.inject.Inject

/**
 * Created by WonSeok on 2022.08.24
 **/
class GetKindUseCase @Inject constructor(private val abandonedPetsRepository: AbandonedPetsRepository,) :
    BaseUseCase<GetKindUseCase.RequestValue, Record<KindEntity>>() {

    override suspend fun run(request: RequestValue): Record<KindEntity> {
        return abandonedPetsRepository.getKind(request.upKindCd)
    }

    data class RequestValue(val upKindCd: String)
}