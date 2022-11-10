package wwon.seokk.abandonedpets

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp
import wwon.seokk.abandonedpets.util.SettingsPref
import javax.inject.Inject

/**
 * Created by WonSeok on 2022.08.02
 **/
@HiltAndroidApp
class BaseApplication: Application() {
    @Inject
    lateinit var settingsPref: SettingsPref
    override fun onCreate() {
        super.onCreate()
        val theme = settingsPref.getTheme()
        AppCompatDelegate.setDefaultNightMode(
            when(theme) {
                "light" -> AppCompatDelegate.MODE_NIGHT_NO
                "dark" -> AppCompatDelegate.MODE_NIGHT_YES
                else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            }
        )
    }
}