package wwon.seokk.abandonedpets.ui.settings

import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import wwon.seokk.abandonedpets.R
import wwon.seokk.abandonedpets.util.SettingsPref
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsPref: SettingsPref
): ViewModel() {
    val themes = listOf(
        Theme.LIGHT,
        Theme.DARK,
        Theme.DEFAULT
    )
    val selectedTheme = mutableStateOf(Theme.DEFAULT)

    init {
        selectedTheme.value = themes.find { it.id == settingsPref.getTheme() }!!
    }

    fun setTheme(theme: Theme) {
        selectedTheme.value = theme
        settingsPref.setTheme(theme.id)
        AppCompatDelegate.setDefaultNightMode(
            when(theme) {
                Theme.LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
                Theme.DARK -> AppCompatDelegate.MODE_NIGHT_YES
                else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            }
        )
    }

    data class Theme(
        val id: String,
        @StringRes val text: Int
    ) {
        companion object{
            val LIGHT = Theme("light", R.string.settings_screen_theme_light)
            val DARK = Theme("dark", R.string.settings_screen_theme_dark)
            val DEFAULT = Theme("system", R.string.settings_screen_theme_system)
        }
    }
}