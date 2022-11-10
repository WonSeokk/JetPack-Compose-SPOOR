package wwon.seokk.abandonedpets.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import wwon.seokk.abandonedpets.util.SettingsPref
import javax.inject.Singleton

/**
 * Created by WonSeok on 2022.08.02
 **/
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideSettingsPref(context: Application) = SettingsPref(context)

    @Provides
    fun providesContext(@ApplicationContext context: Context) = context

    @Provides
    fun providesDispatcher() = Dispatchers.IO
}