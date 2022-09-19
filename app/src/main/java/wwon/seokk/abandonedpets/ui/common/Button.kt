package wwon.seokk.abandonedpets.ui.common

import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import wwon.seokk.abandonedpets.ui.theme.AbandonedPetsTheme

/**
 * Created by WonSeok on 2022.09.16
 **/
@Composable
fun FavoriteButton(
    isLiked: Boolean,
    modifier: Modifier = Modifier,
    contentAlpha: Float = ContentAlpha.high,
    onClick: () -> Unit
) {
    CompositionLocalProvider(LocalContentAlpha provides contentAlpha) {
        IconToggleButton(
            checked = isLiked,
            onCheckedChange = { onClick() }
        ) {
            Icon(
                imageVector = if (isLiked) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                tint = AbandonedPetsTheme.colors.redColor,
                contentDescription = null
            )
        }
    }
}

@Composable
fun BackButton(tint: Color = AbandonedPetsTheme.colors.surfaceColor, onClick: () -> Unit) {
    IconButton(onClick) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            tint = tint,
            contentDescription = null
        )
    }
}

@Composable
fun ShareButton(onClick: () -> Unit) {
    IconButton(onClick) {
        Icon(
            imageVector = Icons.Filled.Share,
            tint = AbandonedPetsTheme.colors.surfaceColor,
            contentDescription = null
        )
    }
}

@Composable
fun CloseButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(modifier = modifier, onClick = onClick) {
        Icon(
            imageVector = Icons.Filled.Close,
            modifier = Modifier.size(32.dp),
            tint = AbandonedPetsTheme.colors.surfaceOppositeColor,
            contentDescription = null
        )
    }
}


@Composable
fun BackdropButton(isRevealed: Boolean, modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(modifier = modifier, onClick = onClick) {
        Icon(
            imageVector = if(isRevealed) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
            tint = AbandonedPetsTheme.colors.surfaceOppositeColor,
            contentDescription = null
        )
    }
}


@Composable
fun ArrowIcon() {
    Icon(
        imageVector = Icons.Filled.KeyboardArrowRight,
        tint = AbandonedPetsTheme.colors.surfaceOppositeColor,
        contentDescription = null
    )
}