package com.luis.aplicacionplucho.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.luis.aplicacionplucho.data.local.CartDatabase
import com.luis.aplicacionplucho.data.local.CartEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CartViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = CartDatabase.getDatabase(application).cartDao()

    var nombreCliente by mutableStateOf("")
        private set

    fun actualizarNombreCliente(nombre: String) {
        nombreCliente = nombre
    }

    val cartItems: StateFlow<List<CartEntity>> = dao.getCartItems()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val totalPrice: StateFlow<Double> = cartItems.map { list ->
        list.sumOf { it.price * it.quantity }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0.0)

    fun addToCart(productId: Int, name: String, price: Double, quantity: Int = 1) {
        viewModelScope.launch {
            val currentList = cartItems.value
            val existing = currentList.find { it.productId == productId }
            val newQuantity = (existing?.quantity ?: 0) + quantity

            dao.insertOrUpdate(CartEntity(productId, name, price, newQuantity))
        }
    }

    fun updateQuantity(item: CartEntity, change: Int) {
        viewModelScope.launch {
            val newQty = item.quantity + change
            if (newQty > 0) {
                dao.insertOrUpdate(item.copy(quantity = newQty))
            } else {
                dao.delete(item)
            }
        }
    }

    fun removeItem(item: CartEntity) {
        viewModelScope.launch { dao.delete(item) }
    }

    fun clearCart() {
        viewModelScope.launch { dao.clearCart() }
    }
}