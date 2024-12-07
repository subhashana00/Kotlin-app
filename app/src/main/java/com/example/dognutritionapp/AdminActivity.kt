package com.example.dognutritionapp


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dognutritionapp.R


class AdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)



// Find views
        val buttonAdd = findViewById<Button>(R.id.adminbuttonAdd)
        val buttonDelete = findViewById<Button>(R.id.adminbuttonDelete)
        val buttonUpdate = findViewById<Button>(R.id.adminbuttonUpdate)
        val categoryAdd = findViewById<Button>(R.id.categoryadd)
        // Set button click listeners
        buttonAdd.setOnClickListener {
            // Start the Add Food Activity
            val intent = Intent(this, AddFoodActivity::class.java)
            startActivity(intent)
        }

        buttonDelete.setOnClickListener {
            // Handle delete food logic here
        }

        buttonUpdate.setOnClickListener {
            // Handle update food logic here
        }

        categoryAdd.setOnClickListener {
            val intent = Intent(this, AddCategory::class.java)
            startActivity(intent)
        }

    }
}