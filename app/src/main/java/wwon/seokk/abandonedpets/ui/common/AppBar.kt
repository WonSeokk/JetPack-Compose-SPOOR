package wwon.seokk.abandonedpets.ui.common

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import wwon.seokk.abandonedpets.ui.theme.AbandonedPetsTheme


/**
 * Created by WonSeok on 2022.08.15
 **/
@Composable
fun HomeAppBar(
    title: String,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            Text( text = title)
        },
        modifier = modifier,
        backgroundColor = AbandonedPetsTheme.colors.surfaceVariantColor,
        elevation = 0.dp
    )
}

@Preview
@Composable
fun HomeAppBarPreview() {
    AbandonedPetsTheme{
        HomeAppBar(title = "SPOOR")
    }
}