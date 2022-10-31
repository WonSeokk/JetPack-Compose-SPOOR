package wwon.seokk.abandonedpets.ui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import wwon.seokk.abandonedpets.ui.common.BottomDivider
import wwon.seokk.abandonedpets.ui.common.NavigateUpAppBar
import wwon.seokk.abandonedpets.ui.theme.AbandonedPetsTheme

@Composable
fun SettingsScreen(openLibs: () -> Unit, navigateBack: () -> Unit) {
    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        topBar = {
            NavigateUpAppBar(title = "설정", navigateBack = navigateBack)
        },
        content = { SettingsContent(openLibs) }
    )
}

@Composable
fun SettingsContent(openLibs: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Column(modifier = Modifier.clickable {
                openLibs.invoke()
            }) {
                Text(
                    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
                    text = "오픈소스 라이선스",
                    style = AbandonedPetsTheme.typography.body1.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                )
                BottomDivider()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainContentPreView() {
    AbandonedPetsTheme {
        SettingsContent { }
    }
}
