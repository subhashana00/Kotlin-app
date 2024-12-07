package com.example.dognutritionapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dognutritionapp.DbConfig.BlogContent
import com.example.dognutritionapp.R

class ContentAdapter( private val contentList : List<BlogContent>):RecyclerView.Adapter<ContentAdapter.BlogViewHolder>(){


    inner class BlogViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        private  var blogTitle:TextView = itemView.findViewById(R.id.blogTitle)
        private  var blogDescription:TextView= itemView.findViewById(R.id.blogDescription)
        private  var blogImage:ImageView= itemView.findViewById(R.id.blogImage)

        fun bind (contentList: BlogContent){
            blogTitle.text= contentList.title
            blogDescription.text= contentList.description

            Glide.with(itemView.context)
                .load(contentList.contentUrl)
                .into(blogImage)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentAdapter.BlogViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.content_holder,parent,false)
    return BlogViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: ContentAdapter.BlogViewHolder, position: Int) {
        holder.bind(contentList[position])
    }

    override fun getItemCount(): Int {
        return contentList.size
    }
}