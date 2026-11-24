package com.luis.aplicacionplucho.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Query("SELECT * FROM cart_table")
    fun getCartItems(): Flow<List<CartEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(item: CartEntity)

    @Delete
    suspend fun delete(item: CartEntity)

    @Query("DELETE FROM cart_table")
    suspend fun clearCart()
}