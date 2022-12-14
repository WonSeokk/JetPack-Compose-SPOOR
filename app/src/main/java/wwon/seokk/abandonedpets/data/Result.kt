package wwon.seokk.abandonedpets.data

import wwon.seokk.abandonedpets.data.remote.RemoteException

/**
 * Created by WonSeok on 2022.08.25
 **/
sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: RemoteException) : Result<Nothing>()
}