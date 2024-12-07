package com.example.dognutritionapp.DbConfig

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface Cart_DAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cart: Cart)

    @Query("SELECT * FROM cart_table WHERE userId = :userId")
    fun getOrdersByUser(userId: Int): LiveData<List<Cart>>
}