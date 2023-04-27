package com.example.sweetsboutiqueshop.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_product_data_table")
class CategoriesProductsModel (

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "category_product_id")
    var id:Int,

    @ColumnInfo(name = "category_product_id_product")
    var productId : Int,

    @ColumnInfo(name = "category_product_id_category")
    var categoryId: Int

)