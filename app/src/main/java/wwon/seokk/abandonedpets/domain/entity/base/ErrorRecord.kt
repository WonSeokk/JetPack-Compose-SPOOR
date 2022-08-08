package wwon.seokk.abandonedpets.domain.entity.base

/**
 * Created by WonSeok on 2022.08.02
 **/
sealed class ErrorRecord {
    object ClientError: ErrorRecord() {
        override fun toString(): String {
            return "Error from client"
        }
    }
    object ServerError: ErrorRecord() {
        override fun toString(): String {
            return "Error from server"
        }
    }
    object NetworkError: ErrorRecord() {
        override fun toString(): String {
            return "Network Error"
        }
    }
    object GenericError: ErrorRecord() {
        override fun toString(): String {
            return "There was some error"
        }
    }
}
