package wwon.seokk.abandonedpets.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import javax.annotation.concurrent.Immutable
/**
 * Created by WonSeok on 2022.08.15
 **/

val BottomSheetShape = RoundedCornerShape(
    topStart = 20.dp,
    topEnd = 20.dp,
    bottomStart = 0.dp,
    bottomEnd = 0.dp
)

@Immutable
data class AbandonedPetsShapes(
    val bottomSheetShape: Shape,
    val smallRoundCornerShape: Shape,
    val mediumRoundCornerShape: Shape,
    val largeRoundCornerShape: Shape,
    val circleRoundCornerShape: Shape
)

val LocalAbandonedPetsShapes = staticCompositionLocalOf {
    AbandonedPetsShapes(
        bottomSheetShape = RoundedCornerShape(ZeroCornerSize),
        smallRoundCornerShape = RoundedCornerShape(ZeroCornerSize),
        mediumRoundCornerShape = RoundedCornerShape(ZeroCornerSize),
        largeRoundCornerShape = RoundedCornerShape(ZeroCornerSize),
        circleRoundCornerShape = RoundedCornerShape(ZeroCornerSize)
    )
}