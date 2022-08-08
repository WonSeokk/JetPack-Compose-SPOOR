package wwon.seokk.abandonedpets.domain.entity.base

/**
 * Created by WonSeok on 2022.08.02
 **/
data class Record<out R>(
    val data: R?,
    val error: ErrorRecord?
)
