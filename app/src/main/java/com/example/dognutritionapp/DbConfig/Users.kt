package com.example.dognutritionapp.DbConfig

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName="user_table")
data class Users(

    @PrimaryKey(autoGenerate = true) val userId: Int = 0,

    @ColumnInfo(name = "user_name") var name: String,

    @ColumnInfo(name = "user_email") var email: String,

    @ColumnInfo(name = "user_password") var password: String,

    @ColumnInfo(name = "user_address") var address: String,
    @ColumnInfo(name = "user_Type") var userType: String,

    )