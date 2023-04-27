package com.example.sweetsboutiqueshop.data.localDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.sweetsboutiqueshop.data.models.CategoriesModel
import com.example.sweetsboutiqueshop.data.models.CategoriesProductsModel

@Dao
interface CategoriesDao {

    @Insert
    suspend fun insert(categoriesModel: CategoriesModel)

    @Query("SELECT * FROM category_data_table")
    fun getAllCategories(): LiveData<List<CategoriesModel>>

    @Query("SELECT * FROM category_data_table WHERE category_id IN (:idsCategories)")
    fun loadCategoriesProduct(idsCategories:List<Int>): LiveData<List<CategoriesModel>>

    @Query("DELETE FROM category_data_table")
    suspend fun clear()
}