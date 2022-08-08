package wwon.seokk.abandonedpets.data.remote.mapper

import android.util.Log
import wwon.seokk.abandonedpets.data.remote.RemoteException
import wwon.seokk.abandonedpets.domain.entity.base.ErrorRecord
import wwon.seokk.abandonedpets.domain.entity.base.Record

/**
 * Created by WonSeok on 2022.08.02
 **/
class ErrorMapper {

    fun <T>mapErrorRecord(e: RemoteException): Record<T> {
        Log.e(ErrorMapper::class.simpleName, e.message.toString())
        val errorRecord: ErrorRecord = when(e) {
            is RemoteException.ClientError -> ErrorRecord.ClientError
            is RemoteException.ServerError -> ErrorRecord.ServerError
            is RemoteException.NoNetworkError -> ErrorRecord.NetworkError
            else -> ErrorRecord.GenericError
        }
        return Record(null, errorRecord)
    }
}