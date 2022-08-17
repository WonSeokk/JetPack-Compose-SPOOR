package wwon.seokk.abandonedpets.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle

/**
 * Created by WonSeok on 2022.08.18
 **/

data class AbandonedPetsTypography(
    val title1: TextStyle,
    val title2: TextStyle,
    val body1: TextStyle,
    val body2: TextStyle
)

val LocalAbandonedPetsTypography = staticCompositionLocalOf {
    AbandonedPetsTypography(
        title1 = TextStyle.Default,
        title2 = TextStyle.Default,
        body1 = TextStyle.Default,
        body2 = TextStyle.Default
    )
}