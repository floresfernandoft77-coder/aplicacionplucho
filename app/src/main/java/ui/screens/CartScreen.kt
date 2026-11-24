package com.luis.aplicacionplucho.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.luis.aplicacionplucho.data.local.CartEntity
import com.luis.aplicacionplucho.ui.viewmodel.CartViewModel
import com.luis.aplicacionplucho.utils.WhatsAppHelper

@Composable
fun CartScreen(cartViewModel: CartViewModel) {
    val context = LocalContext.current
    val cartItems by cartViewModel.cartItems.collectAsState()
    val total by cartViewModel.totalPrice.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Carrito de Compras", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        if (cartItems.isEmpty()) {
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                Text("Tu carrito está vacío 🍵", fontSize = 16.sp)
            }
        } else {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(cartItems) { item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFDF0F0)),
                        elevation = CardDefaults.cardElevation(0.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(item.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                Text("S/. ${"%.2f".format(item.price)}", color = Color.Gray)
                            }

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                IconButton(
                                    onClick = { cartViewModel.updateQuantity(item, -1) },
                                    modifier = Modifier.size(32.dp)
                                ) {
                                    Text("-", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                                }

                                Text(
                                    text = "x${item.quantity}",
                                    modifier = Modifier.padding(horizontal = 8.dp),
                                    fontWeight = FontWeight.Bold
                                )

                                IconButton(
                                    onClick = { cartViewModel.updateQuantity(item, 1) },
                                    modifier = Modifier.size(32.dp)
                                ) {
                                    Text("+", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                                }

                                IconButton(
                                    onClick = { cartViewModel.removeItem(item) },
                                    modifier = Modifier.size(32.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Eliminar",
                                        tint = Color.Red
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Total: S/. ${"%.2f".format(total)}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                OutlinedButton(
                    onClick = { cartViewModel.clearCart() },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red)
                ) {
                    Text("Cancelar")
                }

                Spacer(modifier = Modifier.width(12.dp))

                Button(
                    onClick = {
                        // Llamada correcta al helper de WhatsApp
                        WhatsAppHelper.sendOrder(
                            context = context,
                            items = cartItems,
                            total = total
                        )
                    },
                    modifier = Modifier.weight(2f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B4513))
                ) {
                    Text("Enviar por WhatsApp 📲", color = Color.White)
                }
            }
        }
    }
}