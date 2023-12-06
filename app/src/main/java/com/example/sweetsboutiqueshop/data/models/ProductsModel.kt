package com.example.sweetsboutiqueshop.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_data_table")
data class ProductsModel  (

    @PrimaryKey(autoGenerate = false)

    @ColumnInfo(name = "product_id")
    val id:Int,
    @ColumnInfo(name = "product_name")
    val name:String,
    @ColumnInfo(name = "product_main_image")
    val mainImage:String,
    @ColumnInfo(name = "product_price")
    val price:Int,
    @ColumnInfo(name = "product_sale_price")
    val salePrice :Int,
    @ColumnInfo(name = "product_description")
    val description:String,
    @ColumnInfo(name = "product_delivery_time")
    val deliveryTime:String
)