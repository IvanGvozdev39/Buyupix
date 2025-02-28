package com.test.buyupix.presentation.navigation

sealed class Screen(val route: String) {
    data object LoginScreen: Screen("login_screen")
}
