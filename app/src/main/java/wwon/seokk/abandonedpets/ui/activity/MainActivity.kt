package wwon.seokk.abandonedpets.ui.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import wwon.seokk.abandonedpets.ui.AbandonedPetsApp

/**
 * Created by WonSeok on 2022.08.02
 **/
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            AbandonedPetsApp()
        }
    }
}