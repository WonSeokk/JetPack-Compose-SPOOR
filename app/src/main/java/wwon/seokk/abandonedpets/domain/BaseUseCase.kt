package wwon.seokk.abandonedpets.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * Created by WonSeok on 2022.08.18
 **/
abstract class BaseUseCase<REQUEST, RESPONSE> {

    open val dispatcher: CoroutineDispatcher = Dispatchers.IO

    suspend operator fun invoke(
        request: REQUEST
    ): Flow<RESPONSE> = flow {
        emit(run(request = request))
    }.flowOn(dispatcher)

    abstract suspend fun run(request: REQUEST): RESPONSE
}