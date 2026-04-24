package com.example.pawparazzi

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

class MainViewModel : ViewModel() {
    val backStack = NavBackStack<NavKey>(Feed)
    var isDetailOpen by mutableStateOf(false)
}
