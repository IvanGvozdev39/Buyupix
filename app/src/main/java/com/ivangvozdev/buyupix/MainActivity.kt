    package com.ivangvozdev.buyupix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ivangvozdev.buyupix.presentation.navigation.Navigation
import com.ivangvozdev.buyupix.ui.theme.BuyupixTheme
import dagger.hilt.android.AndroidEntryPoint

    @AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            BuyupixTheme {
                Navigation()
            }
        }
    }
}