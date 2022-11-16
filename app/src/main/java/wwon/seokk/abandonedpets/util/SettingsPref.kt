package wwon.seokk.abandonedpets.util

import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Created by WonSeok on 2022.11.10
 **/
class SettingsPref(context: Context): CachedPreference(context, PrefKeys.Settings.FILE_NAME) {
    private val _currentTheme = MutableStateFlow(getTheme())
    val currentTheme: StateFlow<String>
        get() = _currentTheme.asStateFlow()

    suspend fun setTheme(theme: String) {
        put(PrefKeys.Settings.KEY_THEME, theme)
        _currentTheme.emit(theme)
    }
    fun getTheme() = get(PrefKeys.Settings.KEY_THEME, "system") as String

}