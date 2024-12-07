package com.example.dognutritionapp

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dognutritionapp.DbConfig.Category
import com.example.dognutritionapp.DbConfig.DogFoodDB
import com.example.dognutritionapp.DbConfig.DogFoodViewModel
import com.example.dognutritionapp.DbConfig.DogFoodViewModelFactory
import com.example.dognutritionapp.DbConfig.PetFoods
import com.example.dognutritionapp.Repositories.AppRepository
import com.example.dognutritionapp.adapters.CategoryAdapter
import com.example.dognutritionapp.adapters.FoodSearchFilterListAdapter

class HomeScreen : AppCompatActivity() {

    lateinit var FoodRecycler:RecyclerView
    lateinit var viewModel: DogFoodViewModel
    lateinit var petFoodAdapter: FoodSearchFilterListAdapter
    lateinit var categoryAdapter: CategoryAdapter
    private val REQUEST_READ_STORAGE = 101
    lateinit var categoryrecyclerView:RecyclerView
    private var userId: Int = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        // Check for permission at runtime based on the Android version
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_MEDIA_IMAGES)
                != PackageManager.PERMISSION_GRANTED
            ) {
                requestStoragePermission()
            }
        } else {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
            ) {
                requestStoragePermission()
            }
        }

        val repository = AppRepository(DogFoodDB.getInstance(applicationContext))
        val factory = DogFoodViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(DogFoodViewModel::class.java)

        val getname = intent.getStringExtra("userName")
        val displaytxt = findViewById<TextView>(R.id.userName)

        // Get user ID from intent extras
        userId = intent.getIntExtra("USER_ID", -1)

        if (userId == -1) {
            Toast.makeText(this, "User ID not found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        displaytxt.text=getname

        initializePetFood()
        initializeCategory()



            val listNavBtn = findViewById<LinearLayout>(R.id.nav1)
            val cartNavBtn = findViewById<LinearLayout>(R.id.nav2)
            val blogNavBtn = findViewById<LinearLayout>(R.id.nav3)
            val orderNavBtn = findViewById<LinearLayout>(R.id.nav4)
            val profileNavBtn = findViewById<LinearLayout>(R.id.nav5)


            blogNavBtn.setOnClickListener {
                val intent = Intent(this, ContentActivity::class.java)
                startActivity(intent)
            }

//            cartNavBtn.setOnClickListener {
//                val intent = Intent(this, CartActivity::class.java).apply {
//                    putExtra("userId", userId)
//                }
//                startActivity(intent)
//            }


            listNavBtn.setOnClickListener {
                val intent = Intent(this, FoodListActivity::class.java)
                startActivity(intent)
            }


            orderNavBtn.setOnClickListener {
                val intent = Intent(this, HomeScreen::class.java)
                startActivity(intent)
            }


//            profileNavBtn.setOnClickListener {
//                val intent = Intent(this, UserSettingActivity::class.java)
//                startActivity(intent)
//            }

    }

    private fun requestStoragePermission() {
        val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arrayOf(android.Manifest.permission.READ_MEDIA_IMAGES)
        } else {
            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        ActivityCompat.requestPermissions(this, permissions, REQUEST_READ_STORAGE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_READ_STORAGE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with loading images
                Toast.makeText(this, "Permission granted. You can now access images.", Toast.LENGTH_SHORT).show()
            } else {
                // Permission denied; notify the user
                Toast.makeText(this, "Storage permission is required to display images", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun initializePetFood(){
//        progressBarPetFood=findViewById(R.id.progressBarFood)
        FoodRecycler = findViewById(R.id.foodRecycler)
//        progressBarPetFood.visibility = View.VISIBLE


        viewModel.allFood.observe(this) { petfood ->
            FoodRecycler.layoutManager = GridLayoutManager(this,2)
            petFoodAdapter = FoodSearchFilterListAdapter(petfood) { selectedItem: PetFoods ->
                // Create an Intent to start FoodDetailActivity
                val intent = Intent(this, FoodDetailActivity::class.java).apply {
                    putExtra("foodName", selectedItem.name)
                    putExtra("foodImage", selectedItem.imageUri)
                    putExtra("foodPrice", selectedItem.price)
                    putExtra("foodDescription", selectedItem.description)

                    putExtra("foodId", selectedItem.foodId)
                    putExtra("userId", userId)
                }
                startActivity(intent)

            }
            FoodRecycler.adapter = petFoodAdapter
//            progressBarPetFood.visibility = View.GONE
        }

    }



    private fun initializeCategory(){
        categoryrecyclerView = findViewById(R.id.categoryRecycler)


        viewModel.allCategories.observe(this) { category ->
            categoryrecyclerView.layoutManager = LinearLayoutManager(this@HomeScreen,
                LinearLayoutManager.HORIZONTAL,false)
            // Set up the adapter with the fetched Category data
            categoryAdapter = CategoryAdapter(category) { selectedItem: Category ->
                val intent = Intent(this, FoodListActivity::class.java)
                intent.putExtra("categoryId", selectedItem.categoryId)
                startActivity(intent)
            }
            categoryrecyclerView.adapter = categoryAdapter

        }

    }
}