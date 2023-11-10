package com.example.sweetsboutiqueshop.data.repository.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.sweetsboutiqueshop.data.models.CategoriesProductsModel
import com.example.sweetsboutiqueshop.data.repository.dataSource.CategoriesProductsApiDataSource
import com.example.sweetsboutiqueshop.data.repository.dataSource.CategoriesProductsDataSource
import com.example.sweetsboutiqueshop.domain.repository.CategoriesProductsCall

class CategoriesProductsRepository(
    private val productsCategoryApiDataSourse: CategoriesProductsApiDataSource,
    private val productsCategoryDataSourse: CategoriesProductsDataSource
) : CategoriesProductsCall {

    override fun loadProductsCategory(idProduct:Int): LiveData<List<CategoriesProductsModel>> {
        return productsCategoryDataSourse.loadProductsCategory(idProduct)
    }

    override suspend fun startMigration(context: Context) {
        productsCategoryDataSourse.clear()
        productsCategoryApiDataSourse.startMigration(context)
    }

}