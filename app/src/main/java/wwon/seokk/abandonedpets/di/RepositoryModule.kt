package wwon.seokk.abandonedpets.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import wwon.seokk.abandonedpets.data.remote.datasource.AbandonedPetsDataSource
import wwon.seokk.abandonedpets.data.repository.AbandonedPetsRepositoryImpl
import wwon.seokk.abandonedpets.domain.repository.AbandonedPetsRepository
import javax.inject.Singleton

/**
 * Created by WonSeok on 2022.08.02
 **/
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesAbandonedPetsRepository(abandonedPetsDataSource: AbandonedPetsDataSource): AbandonedPetsRepository {
        return AbandonedPetsRepositoryImpl(abandonedPetsDataSource)
    }
}