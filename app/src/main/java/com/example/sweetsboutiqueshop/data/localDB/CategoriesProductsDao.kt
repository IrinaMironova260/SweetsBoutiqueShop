package com.example.sweetsboutiqueshop.data.localDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.sweetsboutiqueshop.data.models.CategoriesProductsModel

@Dao
interface CategoriesProductsDao {

    @Insert
    suspend fun insert(model: CategoriesProductsModel)

    @Query("SELECT * FROM category_product_data_table WHERE category_product_id_product=:idProduct")
    fun loadCategoriesProduct(idProduct:Int): LiveData<List<CategoriesProductsModel>>

    @Query("DELETE FROM category_product_data_table")
    suspend fun clear()
}