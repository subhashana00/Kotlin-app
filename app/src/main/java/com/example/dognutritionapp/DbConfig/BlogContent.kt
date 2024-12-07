package com.example.dognutritionapp.DbConfig

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "blog_table")
data class BlogContent(
    @PrimaryKey(autoGenerate = true) val contentId: Int = 0,
    @ColumnInfo(name = "content_title") val title: String,
    @ColumnInfo(name = "content_description") val description: String,
    @ColumnInfo(name = "content_url") val contentUrl: String
)
