package com.example.sweetsboutiqueshop.data.repository.dataSource

import androidx.lifecycle.LiveData
import com.example.sweetsboutiqueshop.data.models.CategoriesProductsModel

interface CategoriesProductsDataSource {
    fun insert(model: CategoriesProductsModel)
    fun loadProductsCategory(idProduct: Int): LiveData<List<CategoriesProductsModel>>
    suspend fun clear()
}