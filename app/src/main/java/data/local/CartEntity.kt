package com.luis.aplicacionplucho.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_table")
data class CartEntity(
    @PrimaryKey val productId: Int,
    val name: String,
    val price: Double,
    val quantity: Int
)