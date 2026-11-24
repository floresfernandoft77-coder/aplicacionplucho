package com.luis.aplicacionplucho.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Email
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String,
    val icon: ImageVector? = null
) {
    object Splash : Screen("splash", "Splash")
    object Home : Screen("home", "Inicio", Icons.Default.Home)
    object Menu : Screen("menu", "Menú", Icons.Default.Menu)
    object Cart : Screen("cart", "Carrito", Icons.Default.ShoppingCart)
    object Location : Screen("location", "Ubicación", Icons.Default.LocationOn)
    object Profile : Screen("profile", "Perfil", Icons.Default.Person)
    object AboutUs : Screen("about_us", "Nosotros", Icons.Default.Info)
    object Contact : Screen("contact", "Contacto", Icons.Default.Email)
    object Detail : Screen("detail/{productId}", "Detalle") {
        fun createRoute(productId: Int) = "detail/$productId"
    }
}