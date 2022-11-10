package wwon.seokk.abandonedpets.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import javax.annotation.concurrent.Immutable
/**
 * Created by WonSeok on 2022.08.15
 **/

object LightThemeColors {
    val PrimaryColor = Color(0xFF87C580)
    val SurfaceColor = Color(0xFFFFFFFF)
    val SurfaceVariantColor = Color(0xFFF2F4F6)
    val SubColor = Color(0xFFF2F3F5)
    val SurfaceOppositeColor = Color(0xFF000000)
    val IconColor = Color(0xFFB2B6BF)
    val RedColor = Color(0xFFE16D6D)
    val OrangeColor= Color(0xFFED7739)
}

object DarkThemeColors {
    val PrimaryColor = Color(0xFF87C580)
    val SurfaceColor = Color(0xFF18171D)
    val SurfaceVariantColor = Color(0xFF101012)
    val SubColor = Color(0xFF2C2C34)
    val SurfaceOppositeColor = Color(0xFFFFFFFF)
    val IconColor = Color(0xFF62626C)
    val RedColor = Color(0xFFE16D6D)
    val OrangeColor= Color(0xFFED7739)
}


@Immutable
data class AbandonedPetsColors(
    val primaryColor: Color,
    val surfaceColor: Color,
    val surfaceVariantColor: Color,
    val surfaceOppositeColor: Color,
    val subColor: Color,
    val iconColor: Color,
    val redColor: Color,
    val orangeColor: Color
)

val LocalAbandonedPetsColors = staticCompositionLocalOf {
    AbandonedPetsColors (
        primaryColor = Color.Blue,
        surfaceColor = Color.White,
        surfaceVariantColor = Color.LightGray,
        subColor = Color.LightGray,
        surfaceOppositeColor = Color.Black,
        iconColor = Color.DarkGray,
        redColor = Color.Red,
        orangeColor = Color.Yellow
    )
}
