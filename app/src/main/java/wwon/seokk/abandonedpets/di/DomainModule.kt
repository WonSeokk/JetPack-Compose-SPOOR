package wwon.seokk.abandonedpets.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import wwon.seokk.abandonedpets.data.remote.model.request.GetAbandonmentPublicRequest
import wwon.seokk.abandonedpets.domain.interatctor.PetsSource
import wwon.seokk.abandonedpets.domain.repository.AbandonedPetsRepository

/**
 * Created by WonSeok on 2022.08.02
 **/
@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {


}