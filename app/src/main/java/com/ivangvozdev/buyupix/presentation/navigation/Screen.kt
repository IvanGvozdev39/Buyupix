package com.ivangvozdev.buyupix.presentation.navigation

sealed class Screen(val route: String) {
    data object LoginScreen: Screen("login_screen")
    data object NoSubscriptionsScreen: Screen("no_subscriptions_screen")
}
