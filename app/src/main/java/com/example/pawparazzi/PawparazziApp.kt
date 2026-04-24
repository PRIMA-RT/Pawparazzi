package com.example.pawparazzi

import android.app.Application
import androidx.compose.ui.ComposeUiFlags
import androidx.compose.ui.ExperimentalComposeUiApi

class PawparazziApp : Application() {

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate() {
        super.onCreate()
        ComposeUiFlags.isMediaQueryIntegrationEnabled = true
    }
}