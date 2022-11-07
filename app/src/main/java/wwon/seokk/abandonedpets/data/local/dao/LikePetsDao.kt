package wwon.seokk.abandonedpets.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import wwon.seokk.abandonedpets.data.local.entities.Pet

/**
 * Created by WonSeok on 2022.11.06
 **/
@Dao
interface LikePetsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPet(pet: Pet): Long

    @Query("SELECT * FROM pet")
    suspend fun getPets(): List<Pet>?

    @Delete
    suspend fun deletePet(pet: Pet): Int

    @Delete
    suspend fun deletePets(pets: List<Pet>)
}