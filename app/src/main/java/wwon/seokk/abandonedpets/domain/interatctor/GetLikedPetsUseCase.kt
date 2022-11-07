package wwon.seokk.abandonedpets.domain.interatctor

import wwon.seokk.abandonedpets.data.local.entities.Pet
import wwon.seokk.abandonedpets.domain.BaseUseCase
import wwon.seokk.abandonedpets.domain.repository.LocalRepository
import javax.inject.Inject

/**
 * Created by WonSeok on 2022.11.07
 **/
class GetLikedPetsUseCase @Inject constructor(private val localRepository: LocalRepository) :
    BaseUseCase<Nothing?, List<Pet>>() {

    override suspend fun run(request: Nothing?): List<Pet> {
        return localRepository.getPets()
    }
    
}