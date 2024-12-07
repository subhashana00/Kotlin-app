package com.example.dognutritionapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FirstView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_first_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val signBtn = findViewById<Button>(R.id.sgnbtn)

        signBtn.setOnClickListener {
            val intent = Intent(this, SignUpFormActivity::class.java)
            startActivity(intent)
        }

        val lgnBtn = findViewById<Button>(R.id.lgnbtn)

        lgnBtn.setOnClickListener {
            val intent = Intent(this, LoginFormActivity::class.java)
            startActivity(intent)
        }

        val bckBtn1 = findViewById<Button>(R.id.backbtn1)

        bckBtn1.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }


    }
}