package wwon.seokk.abandonedpets.ui.settings

import androidx.annotation.StringRes
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
        viewModelScope.launch {
            selectedTheme.value = theme
            settingsPref.setTheme(theme.id)
        }
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