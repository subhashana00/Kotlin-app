package com.example.dognutritionapp.DbConfig

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import com.example.dognutritionapp.Repositories.AppRepository
import kotlinx.coroutines.launch


class DogFoodViewModel(private val repository: AppRepository):ViewModel() {

    val allUsers: LiveData<List<Users>> = repository.allUsers
    val allCategories: LiveData<List<Category>> = repository.allCategories
    val allContent: LiveData<List<BlogContent>> = repository.allContent
    val allFood: LiveData<List<PetFoods>> = repository.getAllFood()


    fun insertUser(users: Users) = viewModelScope.launch {
        repository.insertUser(users)
    }

    fun updateUser(user: Users) = viewModelScope.launch {
        repository.updateUser(user)
    }

    fun deleteUser(user: Users) = viewModelScope.launch {
        repository.deleteUser(user)
    }

    suspend fun getUserById(userId: Int): Users? {
        return repository.getUserById(userId)
    }

    suspend fun getUserByEmailAndPassword(email: String, password: String): Users? {
        return repository.getUserByEmailAndPassword(email, password)
    }

    fun getOrdersByUser(userId: Int): LiveData<List<Cart>> {
        return repository.getOrdersByUser(userId)
    }

    fun insertCategory(category: Category) = viewModelScope.launch {
        repository.insertCategory(category)
    }

    fun getFoodByCategory(categoryId: Int): LiveData<List<PetFoods>> {
        return repository.getFoodByCategory(categoryId)
    }

    fun insertFood(food: PetFoods) = viewModelScope.launch {
        repository.insertFood(food)
    }

    fun updateFood(food: PetFoods) = viewModelScope.launch {
        repository.updateFood(food)
    }

    fun deleteFood(food: PetFoods) = viewModelScope.launch {
        repository.deleteFood(food)
    }

    fun searchFoodByName(name: String): LiveData<List<PetFoods>> {
        return repository.searchFoodByName(name)
    }

    fun searchFoodByCategoryAndName(categoryId: Int, name: String): LiveData<List<PetFoods>> {
        return repository.searchFoodByCategoryAndName(categoryId, name)
    }

    suspend fun getFoodById(foodId: Int): PetFoods? {
        return repository.getFoodById(foodId)
    }

    fun insertContent(content: BlogContent) = viewModelScope.launch {
        repository.insertContent(content)
    }





}