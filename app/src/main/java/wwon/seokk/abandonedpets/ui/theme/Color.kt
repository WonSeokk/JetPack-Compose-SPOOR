package wwon.seokk.abandonedpets.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import javax.annotation.concurrent.Immutable
/**
 * Created by WonSeok on 2022.08.15
 **/

val PrimaryColor = Color(0xFF87C580)
val SurfaceColor = Color(0xFFFFFFFF)
val SurfaceVariantColor = Color(0xFFF2F4F6)
val SurfaceOppositeColor = Color(0xFF000000)
val RedColor = Color(0xFFE16D6D)
val OrangeColor= Color(0xFFED7739)

@Immutable
data class AbandonedPetsColors(
    val primaryColor: Color,
    val surfaceColor: Color,
    val surfaceVariantColor: Color,
    val surfaceOppositeColor: Color,
    val redColor: Color,
    val orangeColor: Color
)

val LocalAbandonedPetsColors = staticCompositionLocalOf {
    AbandonedPetsColors (
        primaryColor = Color.Blue,
        surfaceColor = Color.White,
        surfaceVariantColor = Color.LightGray,
        surfaceOppositeColor = Color.Black,
        redColor = Color.Red,
        orangeColor = Color.Yellow
    )
}
