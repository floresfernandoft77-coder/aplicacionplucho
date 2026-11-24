package com.luis.aplicacionplucho.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.luis.aplicacionplucho.data.local.CartEntity

object WhatsAppHelper {
    private const val PHONE_NUMBER = "51900460380"

    fun sendOrder(context: Context, items: List<CartEntity>, total: Double) {
        if (items.isEmpty()) {
            Toast.makeText(context, "El carrito está vacío", Toast.LENGTH_SHORT).show()
            return
        }

        val builder = StringBuilder()
        builder.append("¡Hola PLUCHO DESAYUNOS! 👋\n")
        builder.append("Quisiera realizar el siguiente pedido:\n\n")

        items.forEach { item ->
            val subtotal = item.price * item.quantity
            builder.append("▪️ ${item.quantity}x ${item.name} - S/. ${String.format("%.2f", subtotal)}\n")
        }

        builder.append("\n*Total a pagar: S/. ${String.format("%.2f", total)}*\n")
        builder.append("¡Gracias!")

        val message = Uri.encode(builder.toString())

        // 1. Intentamos abrir la app nativa de WhatsApp
        val nativeIntent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("whatsapp://send?phone=$PHONE_NUMBER&text=$message")
        }

        // 2. Si la app está instalada, se abre. Si no, se abre el navegador web.
        if (nativeIntent.resolveActivity(context.packageManager) != null) {
            try {
                context.startActivity(nativeIntent)
            } catch (e: Exception) {
                abrirEnNavegador(context, message)
            }
        } else {
            // No tiene WhatsApp instalado, abrimos el enlace web
            abrirEnNavegador(context, message)
        }
    }

    // Función auxiliar para abrir el navegador como plan B
    private fun abrirEnNavegador(context: Context, encodedMessage: String) {
        try {
            val webIntent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://api.whatsapp.com/send?phone=$PHONE_NUMBER&text=$encodedMessage")
            }
            context.startActivity(webIntent)
        } catch (e: Exception) {
            Toast.makeText(context, "Error: Verifica tu conexión a internet", Toast.LENGTH_LONG).show()
        }
    }

    fun openChat(context: Context, customMessage: String = "Hola, me gustaría información de PLUCHO DESAYUNOS.") {
        val message = Uri.encode(customMessage)
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("whatsapp://send?phone=$PHONE_NUMBER&text=$message")
        }

        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        } else {
            abrirEnNavegador(context, message)
        }
    }
}