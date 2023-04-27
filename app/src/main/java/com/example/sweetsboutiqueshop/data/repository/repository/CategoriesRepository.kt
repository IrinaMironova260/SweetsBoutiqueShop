package com.example.sweetsboutiqueshop.data.repository.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.sweetsboutiqueshop.data.models.CategoriesModel
import com.example.sweetsboutiqueshop.data.repository.dataSource.CategoriesApiDataSourse
import com.example.sweetsboutiqueshop.data.repository.dataSource.CategoriesDataSourse
import com.example.sweetsboutiqueshop.domain.repository.CategoryCall

class CategoriesRepository(
    private val categoriesApiDataSourse: CategoriesApiDataSourse,
    private val categoriesDataSourse: CategoriesDataSourse)
    : CategoryCall {

    override fun loadCategories(): LiveData<List<CategoriesModel>> {
        return categoriesDataSourse.loadAllCategories()
    }

    override fun loadCategoriesProduct(idsCategories:List<Int>): LiveData<List<CategoriesModel>>{
        return categoriesDataSourse.loadCategoriesProduct(idsCategories)
    }

    override suspend fun startMigration(context: Context) {
        categoriesDataSourse.clear()
        categoriesApiDataSourse.startMigration(context)
    }

}