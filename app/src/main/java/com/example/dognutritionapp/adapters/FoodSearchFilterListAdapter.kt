package com.example.dognutritionapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dognutritionapp.DbConfig.PetFoods
import com.example.dognutritionapp.R

class FoodSearchFilterListAdapter(private var petFoodList: List<PetFoods>, private val clickListener:
    (PetFoods) -> Unit) : RecyclerView.Adapter<FoodSearchFilterListAdapter.PetFoodViewHolder>() {

    inner class PetFoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val petFoodTitle: TextView = itemView.findViewById(R.id.foodCardTitle)
        private val petFoodPrice: TextView = itemView.findViewById(R.id.foodListPrice)
        private val petFoodImage: ImageView = itemView.findViewById(R.id.foodListImg)

        fun bindPetFoodData(petFoods: PetFoods, clickListener: (PetFoods) -> Unit) {
            petFoodTitle.text = petFoods.name
           var price = petFoods.price.toString()
            petFoodPrice.text = "LKR $price"

            // Load the image using Glide
            Glide.with(itemView.context)
                .load(petFoods.imageUri)
                .into(petFoodImage)

            // Set up click listener
            itemView.setOnClickListener {
                clickListener(petFoods)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetFoodViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.viewholder_food, parent, false)
        return PetFoodViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: PetFoodViewHolder, position: Int) {
        val petFood = petFoodList[position]
        holder.bindPetFoodData(petFood, clickListener)
    }

    override fun getItemCount(): Int {
        return petFoodList.size
    }

    // Update data for filtered or searched results
    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newPetFoodList: List<PetFoods>) {
        petFoodList = newPetFoodList
        notifyDataSetChanged()
    }
}




