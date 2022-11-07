package wwon.seokk.abandonedpets.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import wwon.seokk.abandonedpets.data.local.dao.LikePetsDao
import wwon.seokk.abandonedpets.data.local.entities.Pet
import wwon.seokk.abandonedpets.domain.repository.LocalRepository
import wwon.seokk.abandonedpets.util.calculateNoticeDate

/**
 * Created by WonSeok on 2022.11.06
 **/
class LocalRepositoryImpl constructor(
    private val likePetsDao: LikePetsDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): LocalRepository {

    override suspend fun getPets(): List<Pet> = withContext(ioDispatcher) {
        likePetsDao.getPets()?.filter { calculateNoticeDate(it.noticeEdt).toInt() < 0 }?.run {
            likePetsDao.deletePets(this)
        }
        likePetsDao.getPets() ?: emptyList()
    }

    override suspend fun likePet(pet: Pet, isLike: Boolean): Boolean = withContext(ioDispatcher) {
        return@withContext when(isLike) {
            true -> likePetsDao.insertPet(pet).toInt() != 0
            else -> likePetsDao.deletePet(pet) == 1
        }

    }
}