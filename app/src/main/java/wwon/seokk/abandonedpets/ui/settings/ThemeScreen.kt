package wwon.seokk.abandonedpets.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import wwon.seokk.abandonedpets.R
import wwon.seokk.abandonedpets.ui.common.NavigateUpAppBar
import wwon.seokk.abandonedpets.ui.theme.AbandonedPetsTheme
import wwon.seokk.abandonedpets.util.setStatusBar

/**
 * Created by WonSeok on 2022.11.10
 **/
@Composable
fun ThemeScreen(
    darkTheme: Boolean,
    settingsViewModel: SettingsViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBar(darkTheme.not())
    systemUiController.setSystemBarsColor(AbandonedPetsTheme.colors.surfaceColor)

    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        topBar = {
            NavigateUpAppBar(title = stringResource(id = R.string.screen_settings_theme), navigateBack = navigateBack)
        },
        backgroundColor = AbandonedPetsTheme.colors.surfaceColor,
        content = {
            Box(Modifier
                .padding(12.dp)
                .fillMaxSize()
            ) {
                LazyColumn(
                    modifier = Modifier
                        .background(color = Color.Transparent)
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    items(settingsViewModel.themes.size) { index ->
                        val theme = settingsViewModel.themes[index]
                        val selected = settingsViewModel.selectedTheme
                        val color = if(theme == selected.value) AbandonedPetsTheme.colors.surfaceOppositeColor
                        else AbandonedPetsTheme.colors.iconColor
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .background(color = Color.Transparent)
                                .fillMaxWidth()
                                .height(48.dp)
                                .clip(AbandonedPetsTheme.shapes.smallRoundCornerShape)
                                .clickable(
                                    enabled = true,
                                    onClick = {
                                        settingsViewModel.setTheme(theme)
                                    })
                        ) {
                            Text(
                                text = stringResource(id = theme.text),
                                style = AbandonedPetsTheme.typography.body3.copy(color = color)
                            )
                        }
                    }
                }
            }

        }
    )
}