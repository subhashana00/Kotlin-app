package com.example.dognutritionapp.DbConfig

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dognutritionapp.Repositories.AppRepository
import kotlin.IllegalArgumentException

class DogFoodViewModelFactory (private val repository: AppRepository):ViewModelProvider.Factory{
    override fun <T:ViewModel> create(modelClass: Class<T>):T{
        if (modelClass.isAssignableFrom(DogFoodViewModel::class.java)){
            return DogFoodViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}