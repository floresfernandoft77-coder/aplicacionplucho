package com.luis.aplicacionplucho.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

// --- IMPORTACIONES CORREGIDAS ---
import com.luis.aplicacionplucho.R // <-- AÑADIDO: Para usar R.drawable
import com.luis.aplicacionplucho.navigation.Screen // <-- CORREGIDO: Cambiado com.plucho a com.luis

@Composable
fun SplashScreen(navController: NavController) {
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 1500), label = "SplashAnim"
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(2500)
        navController.popBackStack()
        navController.navigate(Screen.Home.route)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.alpha(alphaAnim.value)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_plucho_logo),
                contentDescription = "Plucho Logo",
                modifier = Modifier.size(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "PLUCHO DESAYUNOS",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Nutrición & Tradición Artesanal",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}