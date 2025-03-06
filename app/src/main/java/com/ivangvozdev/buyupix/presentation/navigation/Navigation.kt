package com.ivangvozdev.buyupix.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ivangvozdev.buyupix.presentation.screens.LoginScreen
import com.ivangvozdev.buyupix.presentation.screens.NoSubscriptionsScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.LoginScreen.route) {
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.NoSubscriptionsScreen.route) {
            NoSubscriptionsScreen()
        }
    }
}