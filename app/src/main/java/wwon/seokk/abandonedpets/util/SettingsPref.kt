package wwon.seokk.abandonedpets.util

import android.content.Context

/**
 * Created by WonSeok on 2022.11.10
 **/
class SettingsPref(context: Context): CachedPreference(context, PrefKeys.Settings.FILE_NAME) {
    fun setTheme(theme: String) = put(PrefKeys.Settings.KEY_THEME, theme)
    fun getTheme() = get(PrefKeys.Settings.KEY_THEME, "system") as String
}