package wwon.seokk.abandonedpets.ui.activity

import android.os.Build
import android.os.Bundle
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import wwon.seokk.abandonedpets.ui.AbandonedPetsApp
import wwon.seokk.abandonedpets.ui.settings.SettingsViewModel
import wwon.seokk.abandonedpets.ui.theme.AbandonedPetsTheme
import wwon.seokk.abandonedpets.util.SettingsPref
import javax.inject.Inject

/**
 * Created by WonSeok on 2022.08.02
 **/
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val themes = listOf(
        SettingsViewModel.Theme.LIGHT,
        SettingsViewModel.Theme.DARK,
        SettingsViewModel.Theme.DEFAULT
    )

    @Inject
    lateinit var settingsPref: SettingsPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var themeState: ThemeUiState by mutableStateOf(ThemeUiState.Loading)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    settingsPref.currentTheme.collectLatest{ theme ->
                        themeState = ThemeUiState.Success(themes.find { f -> f.id == theme }!!)
                    }
                }
            }
        }

        installSplashScreen()
        setContent {
            val darkTheme = shouldUseDarkTheme(themeState)
            AbandonedPetsTheme(darkTheme = darkTheme){
                val widthSizeClass = calculateWindowSizeClass(this).widthSizeClass
                AbandonedPetsApp(darkTheme, widthSizeClass)
            }
        }
        hideSystemUI()
    }

    @Suppress("DEPRECATION")
    private fun hideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, false)

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        } else {
            window.insetsController?.apply {
                systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }
    }
}



@Composable
fun shouldUseDarkTheme(
    uiState: ThemeUiState,
): Boolean = when (uiState) {
    ThemeUiState.Loading -> isSystemInDarkTheme()
    is ThemeUiState.Success -> when (uiState.theme) {
        SettingsViewModel.Theme.LIGHT -> false
        SettingsViewModel.Theme.DARK -> true
        else -> isSystemInDarkTheme()
    }
}

sealed interface ThemeUiState {
    object Loading : ThemeUiState
    data class Success(val theme: SettingsViewModel.Theme) : ThemeUiState
}

