package com.example.fleetsavingtimer.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import com.example.fleetsavingtimer.ui.theme.FleetSavingTimerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setWindowInsets()
        enableEdgeToEdge()
        setContent {
            FleetSavingTimerTheme {
                MainNavHost {
                    finish()
                }
            }
        }
    }

    private fun setWindowInsets() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }
}