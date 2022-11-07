package wwon.seokk.abandonedpets.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import wwon.seokk.abandonedpets.data.local.dao.LikePetsDao
import wwon.seokk.abandonedpets.data.local.entities.Pet
import wwon.seokk.abandonedpets.domain.repository.LocalRepository

/**
 * Created by WonSeok on 2022.11.06
 **/
class LocalRepositoryImpl constructor(
    private val likePetsDao: LikePetsDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): LocalRepository {

    override suspend fun getPets(): List<Pet> = withContext(ioDispatcher) {
        likePetsDao.getPets() ?: emptyList()
    }

    override suspend fun likePet(pet: Pet, isLike: Boolean): Boolean = withContext(ioDispatcher) {
        return@withContext when(isLike) {
            true -> likePetsDao.insertPet(pet).toInt() != 0
            else -> likePetsDao.deletePPet(pet) == 1
        }

    }
}