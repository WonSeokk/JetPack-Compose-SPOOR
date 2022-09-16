package wwon.seokk.abandonedpets.ui.common

import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.CancelPresentation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import wwon.seokk.abandonedpets.ui.theme.AbandonedPetsTheme


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
fun BackButton(onClick: () -> Unit) {
    IconButton(onClick) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            tint = AbandonedPetsTheme.colors.surfaceColor,
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