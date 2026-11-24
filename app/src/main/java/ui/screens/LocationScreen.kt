package com.luis.aplicacionplucho.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LocationScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Nuestra Ubicación", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("📍 Dirección:", fontWeight = FontWeight.Bold)
                Text("Mercado Principal de Tarata")
                Spacer(modifier = Modifier.height(8.dp))

                Text("📞 Teléfono:", fontWeight = FontWeight.Bold)
                Text("900460380")
                Spacer(modifier = Modifier.height(8.dp))

                Text("🕕 Horario de Atención:", fontWeight = FontWeight.Bold)
                Text("06:00 AM - 09:30 AM")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val gmmIntentUri = Uri.parse("geo:0,0?q=Mercado+Principal+de+Tarata")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri).apply {
                    setPackage("com.google.android.apps.maps")
                }
                context.startActivity(mapIntent)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("CÓMO LLEGAR CON GOOGLE MAPS 🗺️")
        }
    }
}
