package com.example.dognutritionapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.dognutritionapp.DbConfig.DogFoodDB
import com.example.dognutritionapp.DbConfig.DogFoodViewModel
import com.example.dognutritionapp.DbConfig.DogFoodViewModelFactory
import com.example.dognutritionapp.DbConfig.Users
import com.example.dognutritionapp.Repositories.AppRepository

class SignUpFormActivity : AppCompatActivity() {

    private lateinit var viewModel: DogFoodViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up_form)

        val repository = AppRepository(DogFoodDB.getInstance(application))
        val factory = DogFoodViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(DogFoodViewModel::class.java)

        val editTextName = findViewById<EditText>(R.id.unameTXT2)
        val editTextEmail = findViewById<EditText>(R.id.emailTXT)
        val editTextPassword = findViewById<EditText>(R.id.passTXT2)
        val editTextAddress = findViewById<EditText>(R.id.addressTXT)
        val editTextUserRole =findViewById<EditText>(R.id.userRoleEdit)
        val buttonRegister = findViewById<Button>(R.id.signBtn)
        val textViewLogin = findViewById<TextView>(R.id.lgnTXT)

        buttonRegister.setOnClickListener {
            val name = editTextName.text.toString().trim()
            val email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()
            val address = editTextAddress.text.toString().trim()
            val userType = editTextUserRole.text.toString().trim()


            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || address.isEmpty() || userType.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val user = Users(name = name, email = email, password = password, address = address, userType = userType )
            viewModel.insertUser(user)
            Toast.makeText(this, "User registered successfully!", Toast.LENGTH_SHORT).show()

            finish()

        }

        textViewLogin.setOnClickListener {
            startActivity(Intent(this, LoginFormActivity::class.java))

        }


    }


}






