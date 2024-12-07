package com.example.dognutritionapp.DbConfig

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface Category_DAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(category: Category)

    @Query("SELECT * FROM category_table")
    fun getAllCategories(): LiveData<List<Category>>
}