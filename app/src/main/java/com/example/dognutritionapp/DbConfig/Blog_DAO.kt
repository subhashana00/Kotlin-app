package com.example.dognutritionapp.DbConfig

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface Blog_DAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(content: BlogContent)

    @Query("SELECT * FROM blog_table")
    fun getAllContent(): LiveData<List<BlogContent>>

}