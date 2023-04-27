package com.example.sweetsboutiqueshop.data.repository.dataSource

import androidx.lifecycle.LiveData
import com.example.sweetsboutiqueshop.data.models.CategoriesModel

interface CategoriesDataSourse {

    fun insert(categoriesModel: CategoriesModel)
    fun loadAllCategories(): LiveData<List<CategoriesModel>>
    fun loadCategoriesProduct(idsCategories:List<Int>): LiveData<List<CategoriesModel>>
    suspend fun clear()
}