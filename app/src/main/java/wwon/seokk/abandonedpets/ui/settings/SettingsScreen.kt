package wwon.seokk.abandonedpets.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import wwon.seokk.abandonedpets.R
import wwon.seokk.abandonedpets.ui.common.BottomDivider
import wwon.seokk.abandonedpets.ui.common.NavigateUpAppBar
import wwon.seokk.abandonedpets.ui.theme.AbandonedPetsTheme

@Composable
fun SettingsScreen(
    openThemes: () -> Unit,
    openLibs: () -> Unit,
    navigateBack: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(AbandonedPetsTheme.colors.surfaceColor)

    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        backgroundColor = AbandonedPetsTheme.colors.surfaceColor,
        topBar = {
            NavigateUpAppBar(title = stringResource(id = R.string.screen_settings), navigateBack = navigateBack)
        },
        content = { SettingsContent(openThemes, openLibs) }
    )
}

@Composable
fun SettingsContent(openThemes: () -> Unit, openLibs: () -> Unit) {
    Surface {
        Column(
            modifier = Modifier
                .background(color = AbandonedPetsTheme.colors.surfaceColor)
                .fillMaxSize()
                .padding(12.dp)
        ) {
            item( modifier = Modifier.clickable { openThemes.invoke() }, text = stringResource(id = R.string.screen_settings_theme) )
            item( modifier = Modifier.clickable { openLibs.invoke() }, text = stringResource(id = R.string.screen_settings_license) )
        }
    }
}

@Composable
fun item(modifier: Modifier, text: String) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
            text = text,
            style = AbandonedPetsTheme.typography.body1.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        )
        BottomDivider()
    }
}

@Preview(showBackground = true)
@Composable
private fun MainContentPreView() {
    AbandonedPetsTheme {
        SettingsContent({},{})
    }
}
