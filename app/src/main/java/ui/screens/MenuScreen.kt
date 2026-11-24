package com.luis.aplicacionplucho.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.luis.aplicacionplucho.R
import com.luis.aplicacionplucho.navigation.Screen
import com.luis.aplicacionplucho.ui.viewmodel.CartViewModel

data class ProductoMenu(
    val id: Int,
    val nombre: String,
    val precio: Double,
    val imagenResId: Int
)

@Composable
fun MenuScreen(cartViewModel: CartViewModel, navController: NavController) {
    val mazamorras = listOf(
        ProductoMenu(1, "Mazamorra de Quinua", 4.00, R.drawable.mazamorra_quinua),
        ProductoMenu(2, "Mazamorra de Habas", 4.00, R.drawable.mazamorra_habas),
        ProductoMenu(3, "Mazamorra de Soya", 4.00, R.drawable.mazamorra_soya),
        ProductoMenu(4, "Mazamorra Morada", 4.00, R.drawable.mazamorra_morada)
    )

    val extras = listOf(
        ProductoMenu(5, "Pan con Palta", 1.50, R.drawable.pan_palta),
        ProductoMenu(6, "Pan con Pollo", 1.50, R.drawable.pan_pollo),
        ProductoMenu(7, "Pan con Saltado de Pollo", 1.50, R.drawable.pan_saltado),
        ProductoMenu(8, "Pan con Torrejas", 1.50, R.drawable.pan_torrejas)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFDF7F7))
    ) {
        Text(
            text = "Nuestro Desayuno 'PLUCHO'",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(mazamorras) { producto ->
                ProductCard(producto) {
                    cartViewModel.addToCart(
                        productId = producto.id,
                        name = producto.nombre,
                        price = producto.precio
                    )
                    navController.navigate(Screen.Cart.route)
                }
            }

            item(span = { GridItemSpan(2) }) {
                Text(
                    text = "Extras para acompañar tu desayuno",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                )
            }

            items(extras) { producto ->
                ProductCard(producto) {
                    cartViewModel.addToCart(
                        productId = producto.id,
                        name = producto.nombre,
                        price = producto.precio
                    )
                    navController.navigate(Screen.Cart.route)
                }
            }
        }
    }
}

@Composable
fun ProductCard(producto: ProductoMenu, onAgregarClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = producto.imagenResId),
                contentDescription = producto.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = producto.nombre,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                maxLines = 2,
                modifier = Modifier.height(40.dp)
            )

            Text(
                text = "S/. ${"%.2f".format(producto.precio)}",
                color = Color(0xFF8B4513),
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = onAgregarClick,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B4513))
            ) {
                Text("Agregar", fontSize = 12.sp, color = Color.White)
            }
        }
    }
}