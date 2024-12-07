package com.example.dognutritionapp.DbConfig

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface UsersDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(users: Users)

    @Update
    suspend fun update(user: Users)

    @Delete
    suspend fun delete(user: Users)

    @Query("SELECT * FROM user_table WHERE userId = :userId")
    suspend fun getUserById(userId: Int): Users?

    @Query("SELECT * FROM user_table WHERE user_email = :email AND user_password = :password")
    suspend fun getUserByEmailAndPassword(email: String, password: String): Users?

    @Query("SELECT * FROM user_table")
    fun getAllUsers(): LiveData<List<Users>>
}