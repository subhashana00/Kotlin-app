package com.example.dognutritionapp.adapters


import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dognutritionapp.DbConfig.Category
import com.example.dognutritionapp.R


class CategoryAdapter(
    private val categoryList: List<Category>,
    private val clickListener: (Category) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {



    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val categoryTitle: TextView = itemView.findViewById(R.id.categoryTxt)
        private val categoryImage: ImageView = itemView.findViewById(R.id.categoryIcon)

        fun bindCategoryData(category: Category, clickListener: (Category) -> Unit) {
            categoryTitle.text = category.name

            // Load the image using Glide without modifying the original color
            Glide.with(itemView.context)
                .load(category.imageUri)
                .into(categoryImage)

            // Set up click listener
            itemView.setOnClickListener {
                clickListener(category)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.viewholder_category, parent, false)
        return CategoryViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categoryList[position]
        holder.bindCategoryData(category, clickListener)

    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}
