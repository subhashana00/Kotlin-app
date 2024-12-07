package com.example.dognutritionapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dognutritionapp.DbConfig.DogFoodDB
import com.example.dognutritionapp.DbConfig.DogFoodViewModel
import com.example.dognutritionapp.DbConfig.DogFoodViewModelFactory
import com.example.dognutritionapp.Repositories.AppRepository
import com.example.dognutritionapp.adapters.FoodSearchFilterListAdapter

class FoodListActivity : AppCompatActivity() {

    private lateinit var viewModel: DogFoodViewModel
    private lateinit var petFoodAdapter: FoodSearchFilterListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchField: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_list)

        val repository = AppRepository(DogFoodDB.getInstance(applicationContext))
        val factory = DogFoodViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(DogFoodViewModel::class.java)

        recyclerView = findViewById(R.id.viewFoodList)
        searchField = findViewById(R.id.foodSearchBar)

        recyclerView.layoutManager = GridLayoutManager(this, 2)
        petFoodAdapter = FoodSearchFilterListAdapter(emptyList()) { selectedFood ->
            val intent = Intent(this, FoodDetailActivity::class.java).apply {
                putExtra("foodName", selectedFood.name)
                putExtra("foodImage", selectedFood.imageUri)
                putExtra("foodPrice", selectedFood.price)
                putExtra("foodDescription", selectedFood.description)
            }
            startActivity(intent)
        }
        recyclerView.adapter = petFoodAdapter

        // Get the selected category ID from intent
        val categoryId = intent.getIntExtra("categoryId", -1)
        if (categoryId != -1) {
            observeFoodByCategory(categoryId)
        }

        setupSearchListener(categoryId)

    }

    private fun observeFoodByCategory(categoryId: Int) {
        viewModel.getFoodByCategory(categoryId).observe(this) { foodList ->
            petFoodAdapter.updateData(foodList)
        }
    }

    private fun setupSearchListener(categoryId: Int) {
        searchField.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString()
                if (categoryId != -1) {
                    viewModel.searchFoodByCategoryAndName(categoryId, query).observe(this@FoodListActivity) { filteredList ->
                        petFoodAdapter.updateData(filteredList)
                    }
                } else {
                    viewModel.searchFoodByName(query).observe(this@FoodListActivity) { filteredList ->
                        petFoodAdapter.updateData(filteredList)
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No implementation needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No implementation needed
            }
        })
    }
}