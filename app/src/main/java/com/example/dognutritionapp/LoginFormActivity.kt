package com.example.dognutritionapp

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.dognutritionapp.DbConfig.DogFoodDB
import com.example.dognutritionapp.DbConfig.DogFoodViewModel
import com.example.dognutritionapp.DbConfig.DogFoodViewModelFactory
import com.example.dognutritionapp.Repositories.AppRepository
import kotlinx.coroutines.launch

class LoginFormActivity : AppCompatActivity() {
    private lateinit var viewModel: DogFoodViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_form)

        val repository = AppRepository(DogFoodDB.getInstance(application))
        val factory = DogFoodViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(DogFoodViewModel::class.java)

        val editTextEmail = findViewById<EditText>(R.id.unameTXT2)
        val editTextPassword = findViewById<EditText>(R.id.passTXT2)
        val buttonLogin = findViewById<Button>(R.id.loginBtn)
        val textViewRegister = findViewById<TextView>(R.id.signtxt)

        textViewRegister.setOnClickListener {
            startActivity(Intent(this, SignUpFormActivity::class.java))
        }

        buttonLogin.setOnClickListener {
            val email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (validateInputs(email, password)) {
                // Launch a coroutine to validate user credentials
                viewModel.viewModelScope.launch {
                    val user = viewModel.getUserByEmailAndPassword(email, password)
                    user?.let {
//                        // Check user type and navigate to the appropriate page
                        val intent = if (user.userType == "Admin") {
                        Intent(this@LoginFormActivity, AdminActivity::class.java)
                    } else {
                        Intent(this@LoginFormActivity, HomeScreen::class.java)
                    }

                        // Pass user ID to the next activity
                        intent.putExtra("USER_ID", user.userId)
                        intent.putExtra("userName", user.name)
                        startActivity(intent)
                        finish()
                    } ?: run {
                        Toast.makeText(this@LoginFormActivity, "Invalid email or password", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun validateInputs(email: String, password: String): Boolean {
        return when {
            email.isEmpty() -> {
                Toast.makeText(this, "Email is required", Toast.LENGTH_SHORT).show()
                false
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                Toast.makeText(this, "Enter a valid email", Toast.LENGTH_SHORT).show()
                false
            }
            password.isEmpty() -> {
                Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show()
                false
            }

            else -> true
        }
    }
}