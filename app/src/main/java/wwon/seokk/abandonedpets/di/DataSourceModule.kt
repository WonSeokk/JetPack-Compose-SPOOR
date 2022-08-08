package wwon.seokk.abandonedpets.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import wwon.seokk.abandonedpets.data.remote.abandonedpets.AbandonedPetsService
import wwon.seokk.abandonedpets.data.remote.datasource.AbandonedPetsDataSource
import wwon.seokk.abandonedpets.data.remote.datasource.AbandonedPetsDataSourceImpl
import wwon.seokk.abandonedpets.domain.interatctor.PetsSource
import wwon.seokk.abandonedpets.domain.repository.AbandonedPetsRepository
import javax.inject.Singleton

/**
 * Created by WonSeok on 2022.08.02
 **/
@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideAbandonedPetsDataSource(
        abandonedPetsService: AbandonedPetsService
    ): AbandonedPetsDataSource {
        return AbandonedPetsDataSourceImpl(abandonedPetsService)
    }
}