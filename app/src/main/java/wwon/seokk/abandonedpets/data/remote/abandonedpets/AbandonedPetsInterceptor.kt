package wwon.seokk.abandonedpets.data.remote.abandonedpets

import okhttp3.Interceptor
import okhttp3.Response
import wwon.seokk.abandonedpets.BuildConfig
import wwon.seokk.abandonedpets.data.remote.ApiConstants
import wwon.seokk.abandonedpets.data.remote.RemoteException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by WonSeok on 2022.08.02
 **/
class AbandonedPetsInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            val requestBuilder = chain.request().newBuilder()
            val originalUrl = chain.request().url
            val updatedUrl = originalUrl.newBuilder()
                .addEncodedQueryParameter(ApiConstants.API_KEY, BuildConfig.API_KEY)
                .addQueryParameter(ApiConstants.TYPE, ApiConstants.JSON)
                .build()
            requestBuilder.url(updatedUrl)
            val response = chain.proceed(requestBuilder.build())
            if (response.code in HttpURLConnection.HTTP_BAD_REQUEST..ApiConstants.STATUS_CODE_499) {
                throw RemoteException.ClientError(response.message)
            } else if (response.code in HttpURLConnection.HTTP_INTERNAL_ERROR..ApiConstants.STATUS_CODE_599) {
                throw RemoteException.ServerError(response.message)
            }
            return response
        } catch (e: Exception) {
            throw  when (e) {
                is UnknownHostException -> RemoteException.NoNetworkError(e.message.toString())
                is SocketTimeoutException -> RemoteException.NoNetworkError(e.message.toString())
                is RemoteException -> e
                else -> RemoteException.GenericError(e.message.toString())
            }
        }
    }
}