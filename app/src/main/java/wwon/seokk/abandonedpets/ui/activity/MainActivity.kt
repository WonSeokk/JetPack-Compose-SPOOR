package wwon.seokk.abandonedpets.ui.activity

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import wwon.seokk.abandonedpets.ui.AbandonedPetsApp
import wwon.seokk.abandonedpets.ui.theme.AbandonedPetsTheme

/**
 * Created by WonSeok on 2022.08.02
 **/
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen()
        setContent {
            AbandonedPetsTheme{
                val widthSizeClass = calculateWindowSizeClass(this).widthSizeClass
                AbandonedPetsApp(widthSizeClass)
            }
        }
    }
}