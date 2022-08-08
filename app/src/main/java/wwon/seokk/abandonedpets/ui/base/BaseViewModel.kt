package wwon.seokk.abandonedpets.ui.base

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container

/**
 * Created by WonSeok on 2022.08.02
 **/
abstract class BaseViewModel<STATE : Any, SIDE_EFFECT : Any>(
    protected val savedStateHandle: SavedStateHandle
) : ContainerHost<STATE, SIDE_EFFECT>, ViewModel() {

    override val container: Container<STATE, SIDE_EFFECT> by lazy {
        container(createInitialState()) {
            initData()
        }
    }

    open fun initData() = intent {  }

    fun uiState(): StateFlow<STATE> = container.stateFlow

    fun uiSideEffect(): Flow<SIDE_EFFECT> = container.sideEffectFlow

    abstract fun createInitialState(): STATE

}