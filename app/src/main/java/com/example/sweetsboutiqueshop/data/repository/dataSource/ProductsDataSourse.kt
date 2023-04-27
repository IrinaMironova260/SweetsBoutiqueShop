package com.example.sweetsboutiqueshop.data.repository.dataSource

import androidx.lifecycle.LiveData
import com.example.sweetsboutiqueshop.data.models.ProductsModel

interface ProductsDataSourse {

    fun insert(productsModel: ProductsModel)
    fun loadAllProduct(): LiveData<List<ProductsModel>>
    suspend fun clear()
}