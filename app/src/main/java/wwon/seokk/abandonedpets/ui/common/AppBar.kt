package wwon.seokk.abandonedpets.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import wwon.seokk.abandonedpets.R
import wwon.seokk.abandonedpets.ui.theme.AbandonedPetsTheme


/**
 * Created by WonSeok on 2022.08.15
 **/
@Composable
fun HomeAppBar(modifier: Modifier = Modifier) {
    TopAppBar(
        title = {
            Image(
                painter = painterResource(id = R.drawable.ic_spoor_logo),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.width(125.dp)
            )
        },
        modifier = modifier,
        backgroundColor = AbandonedPetsTheme.colors.surfaceVariantColor,
        elevation = 0.dp,
        actions = {
            FavoriteButton(isLiked = true, tint = AbandonedPetsTheme.colors.iconColor) {

            }
            SettingButton {

            }
        }
    )
}

@Composable
fun NavigateUpAppBar(
    navigateBack: () -> Unit,
    tint: Color
) {
    BackButton(tint = tint) {  navigateBack.invoke() }
}

@Preview
@Composable
fun HomeAppBarPreview() {
    AbandonedPetsTheme{
        HomeAppBar()
    }
}