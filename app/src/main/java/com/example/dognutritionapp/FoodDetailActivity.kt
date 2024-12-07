package com.example.dognutritionapp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.dognutritionapp.DbConfig.DogFoodDB
import com.example.dognutritionapp.DbConfig.DogFoodViewModel
import com.example.dognutritionapp.DbConfig.DogFoodViewModelFactory
import com.example.dognutritionapp.Repositories.AppRepository

class FoodDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: DogFoodViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_detail)


        val repository = AppRepository(DogFoodDB.getInstance(applicationContext))
        val factory = DogFoodViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(DogFoodViewModel::class.java)

        // Retrieve data from intent
        val foodName = intent.getStringExtra("foodName")
        val foodImageUri = intent.getStringExtra("foodImage")
        val foodPrice = intent.getDoubleExtra("foodPrice", 0.0)
        val foodDescription = intent.getStringExtra("foodDescription")

        // Set data to UI elements
        findViewById<TextView>(R.id.titleTxt).text = foodName
        findViewById<TextView>(R.id.priceTxt).text = "LKR $foodPrice"
        findViewById<TextView>(R.id.descriptionText).text = foodDescription
        Glide.with(this)
            .load(foodImageUri)
            .into(findViewById(R.id.foodDetailImage))
    }
}