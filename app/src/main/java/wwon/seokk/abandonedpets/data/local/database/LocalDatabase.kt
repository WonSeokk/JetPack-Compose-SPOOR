package wwon.seokk.abandonedpets.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import wwon.seokk.abandonedpets.data.local.dao.LikePetsDao
import wwon.seokk.abandonedpets.data.local.entities.Pet

/**
 * Created by WonSeok on 2022.11.06
 **/
@Database(entities = [Pet::class], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun likedPetsDao(): LikePetsDao
}