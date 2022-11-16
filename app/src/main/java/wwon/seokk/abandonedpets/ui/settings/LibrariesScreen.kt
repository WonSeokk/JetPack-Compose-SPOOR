package wwon.seokk.abandonedpets.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mikepenz.aboutlibraries.ui.compose.LibrariesContainer
import com.mikepenz.aboutlibraries.ui.compose.LibraryDefaults
import wwon.seokk.abandonedpets.R
import wwon.seokk.abandonedpets.ui.common.NavigateUpAppBar
import wwon.seokk.abandonedpets.ui.theme.AbandonedPetsTheme

@Composable
fun LibrariesScreen(navigateBack: () -> Unit) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(AbandonedPetsTheme.colors.surfaceColor)

    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        topBar = {
            NavigateUpAppBar(title = stringResource(id = R.string.screen_settings_license), navigateBack = navigateBack)
        },
        content = {
            LibrariesContainer(
                modifier = Modifier
                    .background(color = AbandonedPetsTheme.colors.surfaceColor)
                    .fillMaxSize(),
                colors =  LibraryDefaults.libraryColors(
                    backgroundColor = AbandonedPetsTheme.colors.surfaceColor,
                    contentColor = AbandonedPetsTheme.colors.surfaceOppositeColor,
                    badgeContentColor = AbandonedPetsTheme.colors.surfaceColor,
                    badgeBackgroundColor = AbandonedPetsTheme.colors.primaryColor
                )
            )
        }
    )
}