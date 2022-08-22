package wwon.seokk.abandonedpets.domain.interatctor

import wwon.seokk.abandonedpets.domain.BaseUseCase
import wwon.seokk.abandonedpets.domain.entity.base.Record
import wwon.seokk.abandonedpets.domain.entity.shelter.ShelterEntity
import wwon.seokk.abandonedpets.domain.repository.AbandonedPetsRepository
import javax.inject.Inject

/**
 * Created by WonSeok on 2022.08.22
 **/
class GetShelterUseCase @Inject constructor(private val abandonedPetsRepository: AbandonedPetsRepository,) :
    BaseUseCase<GetShelterUseCase.RequestValue, Record<ShelterEntity>>() {

    override suspend fun run(request: RequestValue): Record<ShelterEntity> {
        return abandonedPetsRepository.getShelter(request.uprCd, request.orgCd)
    }

    data class RequestValue(val uprCd: String, val orgCd: String)
}