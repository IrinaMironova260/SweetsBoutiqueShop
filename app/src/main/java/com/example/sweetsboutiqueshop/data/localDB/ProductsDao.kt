package com.example.sweetsboutiqueshop.data.localDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.sweetsboutiqueshop.data.models.ProductsModel

@Dao
interface ProductsDao {
    @Insert
    suspend fun insert(productModel:ProductsModel)

    @Query("SELECT * FROM product_data_table")
    fun getAllProduct(): LiveData<List<ProductsModel>>

    @Query("SELECT * FROM product_data_table WHERE product_id IN (:idProduct)")
    fun loadInfoProduct(idProduct:Int): LiveData<List<ProductsModel>>

    @Query("DELETE FROM product_data_table")
    suspend fun clear()
}