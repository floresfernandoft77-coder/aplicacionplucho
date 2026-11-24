package com.luis.aplicacionplucho.data.model

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val category: String,
    val ingredients: List<String>,
    val tags: List<String>,
    val imageRes: Int
)