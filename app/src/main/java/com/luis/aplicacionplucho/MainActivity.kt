package com.luis.aplicacionplucho

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.luis.aplicacionplucho.navigation.Screen
import com.luis.aplicacionplucho.ui.screens.*
import com.luis.aplicacionplucho.ui.theme.PluchoDesayunosTheme
import com.luis.aplicacionplucho.ui.viewmodel.CartViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PluchoDesayunosTheme {
                MainAppScreen()
            }
        }
    }
}

@Composable
fun MainAppScreen() {
    val navController = rememberNavController()
    val cartViewModel: CartViewModel = viewModel()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomNavItems = listOf(
        Screen.Home,
        Screen.Menu,
        Screen.Cart,
        Screen.Location,
        Screen.Profile
    )

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.Splash.route) {
                NavigationBar {
                    bottomNavItems.forEach { screen ->
                        NavigationBarItem(
                            selected = currentRoute == screen.route,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(Screen.Home.route) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            label = { Text(screen.title) },
                            icon = { Text("📍") }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Splash.route) { SplashScreen(navController) }
            composable(Screen.Home.route) { HomeScreen(navController) }
            composable(Screen.Menu.route) {
                MenuScreen(cartViewModel = cartViewModel, navController = navController)
            }
            composable(Screen.Cart.route) {
                CartScreen(cartViewModel)
            }
            composable(Screen.Location.route) { LocationScreen() }
            composable(Screen.Profile.route) { ProfileScreen() }
        }
    }
}