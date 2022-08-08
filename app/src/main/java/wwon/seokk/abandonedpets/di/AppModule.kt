package wwon.seokk.abandonedpets.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

/**
 * Created by WonSeok on 2022.08.02
 **/
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun providesContext(@ApplicationContext context: Context) = context

    @Provides
    fun providesDispatcher() = Dispatchers.IO
}