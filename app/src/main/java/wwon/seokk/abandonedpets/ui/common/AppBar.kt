package wwon.seokk.abandonedpets.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import wwon.seokk.abandonedpets.R
import wwon.seokk.abandonedpets.ui.theme.AbandonedPetsTheme


/**
 * Created by WonSeok on 2022.08.15
 **/
@Composable
fun HomeAppBar(settings: () -> Unit, favorite: () -> Unit) {
    TopAppBar(
        title = {
            Image(
                painter = painterResource(id = R.drawable.ic_spoor_logo),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.width(125.dp)
            )
        },
        backgroundColor = AbandonedPetsTheme.colors.surfaceVariantColor,
        elevation = 0.dp,
        actions = {
            FavoriteButton(isLiked = true, tint = AbandonedPetsTheme.colors.iconColor) {
                favorite.invoke()
            }
            SettingButton {
                settings.invoke()
            }
        }
    )
}

@Composable
fun NavigateUpAppBar(
    title: String = "",
    tint: Color = AbandonedPetsTheme.colors.surfaceOppositeColor,
    navigateBack: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically){
        BackButton(tint = tint) {  navigateBack.invoke() }
        Text(
            text = title,
            style = AbandonedPetsTheme.typography.title1.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        )
    }

}

@Preview
@Composable
fun HomeAppBarPreview() {
    AbandonedPetsTheme{
        HomeAppBar({}) { }
    }
}

@Preview
@Preview(showBackground = true)
@Composable
fun NavigateUpPreview() {
    AbandonedPetsTheme{
        NavigateUpAppBar(title = "test") { }
    }
}