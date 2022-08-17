package wwon.seokk.abandonedpets.ui.theme

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
fun AbandonedPetsTheme(content: @Composable () -> Unit) {
    val localAbandonedPetsColors = AbandonedPetsColors(
        surfaceColor = SurfaceColor,
        surfaceVariantColor = SurfaceVariantColor,
        greenColor = GreenColor,
        redColor = RedColor
    )

    val localAbandonedPetsShapes = AbandonedPetsShapes(
        bottomSheetShape = BottomSheetShape,
        smallRoundCornerShape = RoundedCornerShape(6.dp),
        mediumRoundCornerShape = RoundedCornerShape(8.dp),
        largeRoundCornerShape = RoundedCornerShape(10.dp)
    )

    val localAbandonedPetsTypography = AbandonedPetsTypography(
        title1 = TextStyle(
            fontSize = 24.sp,
            lineHeight = 28.8.sp,
            fontWeight = FontWeight.W700
        ),
        title2 = TextStyle(
            fontSize = 22.sp,
            lineHeight = 28.8.sp,
            fontWeight = FontWeight.W700
        ),
        body1 = TextStyle(
            fontSize = 14.sp,
            lineHeight = 16.4.sp,
            fontWeight = FontWeight.W400
        ),
        body2 = TextStyle(
            fontSize = 16.sp,
            lineHeight = 21.6.sp,
            fontWeight = FontWeight.W400
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