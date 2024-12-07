package com.example.dognutritionapp.Repositories

import androidx.lifecycle.LiveData
import com.example.dognutritionapp.DbConfig.BlogContent
import com.example.dognutritionapp.DbConfig.Cart
import com.example.dognutritionapp.DbConfig.Category
import com.example.dognutritionapp.DbConfig.DogFoodDB
import com.example.dognutritionapp.DbConfig.PetFoods
import com.example.dognutritionapp.DbConfig.Users

class AppRepository(private val database: DogFoodDB) {

    // User Operations
    val allUsers: LiveData<List<Users>> = database.userDao().getAllUsers()

    suspend fun insertUser(user: Users) {
        database.userDao().insert(user)
    }
    suspend fun updateUser(user: Users) {
        database.userDao().update(user)
    }

    suspend fun deleteUser(user: Users) {
        database.userDao().delete(user)
    }


    suspend fun getUserById(userId: Int): Users? {
        return database.userDao().getUserById(userId)
    }

    suspend fun getUserByEmailAndPassword(email: String, password: String): Users? {
        return database.userDao().getUserByEmailAndPassword(email, password)
    }

    // Category Operations
    val allCategories: LiveData<List<Category>> = database.categoryDao().getAllCategories()

    suspend fun insertCategory(category: Category) {
        database.categoryDao().insert(category)
    }

    // Food Operations

    fun getAllFood(): LiveData<List<PetFoods>> {
        return database.foodDao().getAllFood()
    }

    fun getFoodByCategory(categoryId: Int): LiveData<List<PetFoods>> {
        return database.foodDao().getFoodByCategory(categoryId)
    }

    suspend fun insertFood(food: PetFoods) {
        database.foodDao().insert(food)
    }

    suspend fun updateFood(food: PetFoods) {
        database.foodDao().update(food)
    }

    suspend fun deleteFood(food: PetFoods) {
        database.foodDao().delete(food)
    }

    fun searchFoodByName(name: String): LiveData<List<PetFoods>> {
        return database.foodDao().searchFoodByName("%$name%")
    }

    fun searchFoodByCategoryAndName(categoryId: Int, name: String): LiveData<List<PetFoods>> {
        return database.foodDao().searchFoodByCategoryAndName(categoryId, "%$name%")
    }

    suspend fun getFoodById(foodId: Int): PetFoods? {
        return database.foodDao().getFoodById(foodId)
    }

    // Blog Content Operations
    val allContent: LiveData<List<BlogContent>> = database.blogDao().getAllContent()

    suspend fun insertContent(content: BlogContent) {
        database.blogDao().insert(content)
    }

    // Cart Operations
    fun getOrdersByUser(userId: Int): LiveData<List<Cart>> {
        return database.cartDao().getOrdersByUser(userId)
    }

    suspend fun insertOrder(cart: Cart) {
        database.cartDao().insert(cart)
    }

}