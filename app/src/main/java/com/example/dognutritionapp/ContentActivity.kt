package com.example.dognutritionapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dognutritionapp.DbConfig.DogFoodDB
import com.example.dognutritionapp.DbConfig.DogFoodViewModel
import com.example.dognutritionapp.DbConfig.DogFoodViewModelFactory
import com.example.dognutritionapp.Repositories.AppRepository
import com.example.dognutritionapp.adapters.ContentAdapter

class ContentActivity : AppCompatActivity() {
    private lateinit var contentRecyclerView: RecyclerView
    private lateinit var viewModel: DogFoodViewModel
    private lateinit var adapter: ContentAdapter
    private lateinit var addContent: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_content)

        val repository = AppRepository(DogFoodDB.getInstance(applicationContext))
        val factory = DogFoodViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(DogFoodViewModel::class.java)

        //Blog Content Initialize
        contentRecyclerView = findViewById(R.id.viewBlog)
        addContent= findViewById(R.id.addBlogBtnImg)

        addContent.setOnClickListener {
            val intent = Intent(this, AddContentActivity::class.java)
            startActivity(intent)
        }


        viewModel.allContent.observe(this){ blog ->

            contentRecyclerView.layoutManager= LinearLayoutManager(this)
            adapter = ContentAdapter(blog)
            contentRecyclerView.adapter= adapter
        }

    }
}