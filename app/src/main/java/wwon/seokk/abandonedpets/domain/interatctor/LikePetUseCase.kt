package wwon.seokk.abandonedpets.domain.interatctor

import wwon.seokk.abandonedpets.data.local.entities.Pet
import wwon.seokk.abandonedpets.domain.BaseUseCase
import wwon.seokk.abandonedpets.domain.repository.LocalRepository
import javax.inject.Inject

/**
 * Created by WonSeok on 2022.11.06
 **/
class LikePetUseCase @Inject constructor(private val localRepository: LocalRepository) :
    BaseUseCase<LikePetUseCase.RequestValue, Boolean>() {

    override suspend fun run(request: RequestValue): Boolean {
        return localRepository.likePet(request.pet, request.isLike)
    }

    data class RequestValue(val pet: Pet, val isLike: Boolean)
}