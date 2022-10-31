package wwon.seokk.abandonedpets.ui.settings

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mikepenz.aboutlibraries.ui.compose.LibrariesContainer
import com.mikepenz.aboutlibraries.ui.compose.LibraryDefaults
import wwon.seokk.abandonedpets.ui.common.NavigateUpAppBar
import wwon.seokk.abandonedpets.ui.theme.AbandonedPetsTheme

@Composable
fun LibrariesScreen(navigateBack: () -> Unit) {
    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        topBar = {
            NavigateUpAppBar(title = "오픈소스 라이선스", navigateBack = navigateBack)
        },
        content = {
            LibrariesContainer(
                modifier = Modifier.fillMaxSize(),
                colors =  LibraryDefaults.libraryColors(
                    badgeContentColor = AbandonedPetsTheme.colors.surfaceColor,
                    badgeBackgroundColor = AbandonedPetsTheme.colors.primaryColor
                )
            )
        }
    )
}