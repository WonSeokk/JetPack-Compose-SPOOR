package wwon.seokk.abandonedpets.ui.base

/**
 * Created by WonSeok on 2022.08.02
 **/
sealed class ScreenState {
    object Loading: ScreenState()
    object Success: ScreenState()
    object Error: ScreenState()
}
