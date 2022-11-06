package wwon.seokk.abandonedpets.domain.repository

import wwon.seokk.abandonedpets.data.local.entities.Pet

/**
 * Created by WonSeok on 2022.11.06
 **/
interface LocalRepository {
    suspend fun getPets(): List<Pet>

    suspend fun likePet(pet: Pet, isLike: Boolean): Boolean
}