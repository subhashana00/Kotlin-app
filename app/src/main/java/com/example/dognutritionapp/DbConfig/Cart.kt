package com.example.dognutritionapp.DbConfig

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_table")
data class Cart(
    @PrimaryKey(autoGenerate = true) val orderId: Int = 0,
    @ColumnInfo(name = "userId") val userId: Int,
    @ColumnInfo(name = "foodId") val foodId: Int,
    @ColumnInfo(name = "order_quantity") val quantity: Int,

)
