package com.example.sweetsboutiqueshop.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "category_data_table")
data class CategoriesModel (

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "category_id")
    var id:Int,
    @ColumnInfo(name = "category_name")
    var name:String,
    @ColumnInfo(name = "category_image")
    var image:String,
    @ColumnInfo(name = "category_description")
    var description:String,
    @ColumnInfo(name = "category_parent_cat")
    var parentCat:Int,
    @ColumnInfo(name = "category_child_cat")
    var childCat :Int
)