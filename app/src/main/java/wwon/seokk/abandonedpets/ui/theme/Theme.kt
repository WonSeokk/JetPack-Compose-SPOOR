package wwon.seokk.abandonedpets.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
/**
 * Created by WonSeok on 2022.08.15
 **/

@Composable
fun AbandonedPetsTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val localAbandonedPetsColors = if(darkTheme) DarkTheme else LightTheme

    val localAbandonedPetsShapes = AbandonedPetsShapes(
        bottomSheetShape = BottomSheetShape,
        smallRoundCornerShape = RoundedCornerShape(6.dp),
        mediumRoundCornerShape = RoundedCornerShape(8.dp),
        largeRoundCornerShape = RoundedCornerShape(10.dp),
        circleRoundCornerShape = RoundedCornerShape(20.dp)
    )

    val localAbandonedPetsTypography = AbandonedPetsTypography(
        title1 = TextStyle(
            fontSize = 24.sp,
            lineHeight = 28.8.sp,
            fontWeight = FontWeight.Bold,
            color = localAbandonedPetsColors.surfaceOppositeColor
        ),
        title2 = TextStyle(
            fontSize = 26.sp,
            lineHeight = 28.8.sp,
            fontWeight = FontWeight.Bold,
            color = localAbandonedPetsColors.surfaceOppositeColor
        ),
        body1 = TextStyle(
            fontSize = 14.sp,
            lineHeight = 16.4.sp,
            fontWeight = FontWeight.W400,
            color = localAbandonedPetsColors.surfaceOppositeColor
        ),
        body2 = TextStyle(
            fontSize = 12.sp,
            lineHeight = 14.6.sp,
            fontWeight = FontWeight.W400,
            color = localAbandonedPetsColors.surfaceOppositeColor
        ),
        body3 = TextStyle(
            fontSize = 18.sp,
            lineHeight = 24.3.sp,
            fontWeight = FontWeight.W400,
            color = localAbandonedPetsColors.surfaceOppositeColor
        )
    )

    CompositionLocalProvider(
        LocalAbandonedPetsColors provides localAbandonedPetsColors,
        LocalAbandonedPetsShapes provides localAbandonedPetsShapes,
        LocalAbandonedPetsTypography provides localAbandonedPetsTypography
    ) {
        MaterialTheme(content = content)
    }
}

private val LightTheme =  AbandonedPetsColors(
    primaryColor = LightThemeColors.PrimaryColor,
    surfaceColor = LightThemeColors.SurfaceColor,
    surfaceVariantColor = LightThemeColors.SurfaceVariantColor,
    subColor = LightThemeColors.SubColor,
    surfaceOppositeColor = LightThemeColors.SurfaceOppositeColor,
    iconColor = LightThemeColors.IconColor,
    redColor = LightThemeColors.RedColor,
    orangeColor = LightThemeColors.OrangeColor
)

private val DarkTheme =  AbandonedPetsColors(
    primaryColor = DarkThemeColors.PrimaryColor,
    surfaceColor = DarkThemeColors.SurfaceColor,
    surfaceVariantColor = DarkThemeColors.SurfaceVariantColor,
    subColor = DarkThemeColors.SubColor,
    surfaceOppositeColor = DarkThemeColors.SurfaceOppositeColor,
    iconColor = DarkThemeColors.IconColor,
    redColor = DarkThemeColors.RedColor,
    orangeColor = DarkThemeColors.OrangeColor
)

object AbandonedPetsTheme {
    val colors: AbandonedPetsColors
        @Composable
        @ReadOnlyComposable
        @NonRestartableComposable
        get() = LocalAbandonedPetsColors.current

    val shapes: AbandonedPetsShapes
        @Composable
        @ReadOnlyComposable
        @NonRestartableComposable
        get() = LocalAbandonedPetsShapes.current

    val typography: AbandonedPetsTypography
        @Composable
        @ReadOnlyComposable
        @NonRestartableComposable
        get() = LocalAbandonedPetsTypography.current
}