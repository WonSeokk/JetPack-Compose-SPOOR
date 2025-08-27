package wwon.seokk.abandonedpets.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntSize
import coil.compose.AsyncImage
import coil.request.ImageRequest
import wwon.seokk.abandonedpets.R
import wwon.seokk.abandonedpets.ui.common.CloseButton
import wwon.seokk.abandonedpets.ui.theme.AbandonedPetsTheme

@Composable
fun ImagePreview(darkTheme: Boolean, uri: String, navigateBack: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize().background(color = AbandonedPetsTheme.colors.surfaceColor)) {
        // Status bar styling now handled by edge-to-edge behavior and theme

        var zoom by remember { mutableStateOf(1f) }
        var offset by remember { mutableStateOf(Offset.Zero) }
        var size by remember { mutableStateOf(IntSize.Zero) }

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(uri)
                .build(),
            contentDescription = stringResource(id = R.string.pet_image_description),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .clipToBounds()
                .pointerInput(Unit) {
                    detectTransformGestures(
                        onGesture = { _, gesturePan, gestureZoom, _ ->
                            val newScale = (zoom * gestureZoom).coerceIn(1f, 3f)
                            val newOffset = offset + gesturePan
                            zoom = newScale

                            val maxX = (size.width * (zoom - 1) / 2f)
                            val maxY = (size.height * (zoom - 1) / 2f)

                            offset = Offset(
                                newOffset.x.coerceIn(-maxX, maxX),
                                newOffset.y.coerceIn(-maxY, maxY)
                            )
                        }
                    )
                }
                .onSizeChanged { size = it }
                .graphicsLayer {
                    translationX = offset.x
                    translationY = offset.y
                    scaleX = zoom
                    scaleY = zoom
                }
                .fillMaxSize()
        )
        CloseButton(modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding()
            .align(Alignment.TopEnd)) { navigateBack.invoke() }
    }
}
