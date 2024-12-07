package com.example.dognutritionapp


import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.dognutritionapp.DbConfig.DogFoodDB
import com.example.dognutritionapp.DbConfig.DogFoodViewModel
import com.example.dognutritionapp.DbConfig.DogFoodViewModelFactory
import com.example.dognutritionapp.DbConfig.PetFoods
import com.example.dognutritionapp.R
import com.example.dognutritionapp.Repositories.AppRepository


class AddFoodActivity : AppCompatActivity() {
    private lateinit var imageViewFood: ImageView
    private lateinit var viewModel: DogFoodViewModel
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_food)

        val repository = AppRepository(DogFoodDB.getInstance(application))
        val factory = DogFoodViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(DogFoodViewModel::class.java)

        // Initialize UI components
        imageViewFood = findViewById(R.id.imageViewFood) // Ensure this matches your XML ID
        val foodNameEditText: EditText = findViewById(R.id.edit_text_food_name)
        val foodDescriptionEditText: EditText = findViewById(R.id.edit_text_food_description)
        val foodPriceEditText: EditText = findViewById(R.id.edit_text_food_price)
        val categoryIdEditText: EditText = findViewById(R.id.edit_text_category_id)
        val addImageButton: Button = findViewById(R.id.button_add_image)
        val addFoodButton: Button = findViewById(R.id.button_add_food)

        addImageButton.setOnClickListener {
            pickImageFromGallery()
        }

        addFoodButton.setOnClickListener {
            val name = foodNameEditText.text.toString()
            val description = foodDescriptionEditText.text.toString()
            val price = foodPriceEditText.text.toString().toDoubleOrNull()
            val categoryId = categoryIdEditText.text.toString().toIntOrNull()

            if (name.isNotEmpty() && description.isNotEmpty() && price != null && categoryId != null && imageUri != null) {
                val foodItem = PetFoods(
                    name = name,
                    description = description,
                    price = price,
                    categoryId = categoryId,
                    imageUri = imageUri.toString()
                )
                viewModel.insertFood(foodItem)
                Toast.makeText(this, "HARI BN", Toast.LENGTH_SHORT).show()
                Intent(this@AddFoodActivity, AdminActivity::class.java)
            } else {
                // Show error message if inputs are invalid
                Toast.makeText(this, "Please fill in all fields and select an image", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_IMAGE_PICK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK) {
            imageUri = data?.data // Get the image URI
            imageViewFood.setImageURI(imageUri) // Display the selected image in the ImageView
        }
    }

    companion object {
        const val REQUEST_IMAGE_PICK = 1001
    }
}