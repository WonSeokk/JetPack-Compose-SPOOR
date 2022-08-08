package wwon.seokk.abandonedpets.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import wwon.seokk.abandonedpets.BuildConfig
import wwon.seokk.abandonedpets.data.remote.ApiConstants
import wwon.seokk.abandonedpets.data.remote.abandonedpets.AbandonedPetsService
import wwon.seokk.abandonedpets.data.remote.abandonedpets.AbandonedPetsInterceptor
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Created by WonSeok on 2022.08.02
 **/
@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    fun providesGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    fun providesAbandonedPetsInterceptor() = AbandonedPetsInterceptor()

    @Provides
    fun providesCache(context: Context): Cache {
        val cacheSize = (10 * 1024 * 1024).toLong() //10 MB
        val httpCacheDirectory = File(context.cacheDir, "http-cache")
        return Cache(httpCacheDirectory, cacheSize)
    }

    @Provides
    fun providesOkHttpClient(retrofitInterceptor: AbandonedPetsInterceptor, cache: Cache): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpClient.addNetworkInterceptor(loggingInterceptor)
        }
        okHttpClient.addInterceptor(retrofitInterceptor)
        okHttpClient.cache(cache)
        okHttpClient.writeTimeout(ApiConstants.TIMEOUT, TimeUnit.SECONDS)
        okHttpClient.readTimeout(ApiConstants.TIMEOUT, TimeUnit.SECONDS)
        okHttpClient.connectTimeout(ApiConstants.TIMEOUT, TimeUnit.SECONDS)
        return okHttpClient.build()
    }

    @Provides
    fun providesAbandonedPetsApi(gson: Gson, okHttpClient: OkHttpClient): AbandonedPetsService =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(AbandonedPetsService::class.java)
}