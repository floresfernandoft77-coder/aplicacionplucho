package com.luis.aplicacionplucho.ui.screens

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.luis.aplicacionplucho.R
import com.luis.aplicacionplucho.VideoActivity // <-- Importamos la nueva pantalla
import com.luis.aplicacionplucho.navigation.Screen
import com.luis.aplicacionplucho.utils.WhatsAppHelper

@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Top Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_plucho_logo),
                    contentDescription = "Logo Small",
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "PLUCHO",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Button(
                onClick = { WhatsAppHelper.openChat(context) },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary)
            ) {
                Text("WhatsApp")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Hero Banner
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "DESAYUNOS 100% NATURALES Y NUTRITIVOS",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Empieza el día con energía gracias a nuestras deliciosas mazamorras de quinua, habas, soya y maíz morado.",
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(16.dp))

                Row {
                    Button(onClick = { navController.navigate(Screen.Menu.route) }) {
                        Text("Ver Menú")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Conoce nuestra preparación", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                val intent = Intent(context, VideoActivity::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("▶ Ver Video Publicitario", fontSize = 16.sp)
        }
    }
}