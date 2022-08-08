package wwon.seokk.abandonedpets.ui.home

/**
 * Created by WonSeok on 2022.08.05
 **/
sealed class HomeSideEffect {
    data class ShowSnackBar(val message: String, val action: String): HomeSideEffect()
}