package com.example.sweetsboutiqueshop.data.repository.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.sweetsboutiqueshop.data.models.CategoriesProductsModel
import com.example.sweetsboutiqueshop.data.repository.dataSource.CategoriesProductsApiDataSource
import com.example.sweetsboutiqueshop.data.repository.dataSource.CategoriesProductsDataSource
import com.example.sweetsboutiqueshop.domain.repository.CategoriesProductsCall

class CategoriesProductsRepository(
    private val categoriesProductsApiDataSourse: CategoriesProductsApiDataSource,
    private val categoriesProductsDataSourse: CategoriesProductsDataSource
) : CategoriesProductsCall {

    override fun loadCategoriesProducts(idProduct:Int): LiveData<List<CategoriesProductsModel>> {
        return categoriesProductsDataSourse.loadCategoriesProduct(idProduct)
    }

    override suspend fun startMigration(context: Context) {
        categoriesProductsDataSourse.clear()
        categoriesProductsApiDataSourse.startMigration(context)
    }

}