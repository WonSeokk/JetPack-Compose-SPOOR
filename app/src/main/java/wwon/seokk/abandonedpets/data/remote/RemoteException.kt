package wwon.seokk.abandonedpets.data.remote

import okio.IOException

/**
 * Created by WonSeok on 2022.08.02
 **/
sealed class RemoteException(message: String): IOException(message) {
    class ClientError(message: String): RemoteException(message)
    class ServerError(message: String): RemoteException(message)
    class NoNetworkError(message: String): RemoteException(message)
    class GenericError(message: String): RemoteException(message)
}
