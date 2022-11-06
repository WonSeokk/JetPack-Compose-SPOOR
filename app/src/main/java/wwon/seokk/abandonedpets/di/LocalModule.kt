package wwon.seokk.abandonedpets.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import wwon.seokk.abandonedpets.data.local.database.LocalDatabase
import wwon.seokk.abandonedpets.data.repository.LocalRepositoryImpl
import wwon.seokk.abandonedpets.domain.repository.LocalRepository
import javax.inject.Singleton

/**
 * Created by WonSeok on 2022.11.06
 **/
@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): LocalDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            LocalDatabase::class.java,
            "Local.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideLocalRepository(
        database: LocalDatabase,
        ioDispatcher: CoroutineDispatcher
    ): LocalRepository {
        return LocalRepositoryImpl(database.likedPetsDao(), ioDispatcher)
    }

}