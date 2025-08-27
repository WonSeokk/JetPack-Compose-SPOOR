package wwon.seokk.abandonedpets.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mikepenz.aboutlibraries.ui.compose.LibrariesContainer
import com.mikepenz.aboutlibraries.ui.compose.LibraryDefaults
import wwon.seokk.abandonedpets.R
import wwon.seokk.abandonedpets.ui.common.NavigateUpAppBar
import wwon.seokk.abandonedpets.ui.theme.AbandonedPetsTheme

@Composable
fun LibrariesScreen(navigateBack: () -> Unit) {
    // Status bar styling now handled by edge-to-edge behavior and theme

    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        topBar = {
            NavigateUpAppBar(title = stringResource(id = R.string.screen_settings_license), navigateBack = navigateBack)
        },
        content = {
            LibrariesContainer(
                modifier = Modifier
                    .padding(it)
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
