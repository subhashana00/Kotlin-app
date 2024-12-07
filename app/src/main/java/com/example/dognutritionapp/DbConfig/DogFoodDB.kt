package com.example.dognutritionapp.DbConfig

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Database


@Database(entities=[Users::class, PetFoods::class, Category::class, Cart::class, BlogContent::class],version=1,exportSchema=false)
abstract class DogFoodDB:RoomDatabase() {

    abstract fun userDao():UsersDAO
    abstract fun foodDao(): PetFood_DAO
    abstract fun categoryDao(): Category_DAO
    abstract fun cartDao(): Cart_DAO
    abstract fun blogDao(): Blog_DAO


    companion object {
        @Volatile
        private var INSTANCE: DogFoodDB? = null

        fun getInstance(context: Context): DogFoodDB {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DogFoodDB::class.java,
                        "Dog_Nutrition_DB"
                    ).build()
                }
                return instance
            }
        }
    }
}
