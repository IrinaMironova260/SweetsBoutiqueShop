package com.example.sweetsboutiqueshop.data.repository.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.sweetsboutiqueshop.data.models.ProductsModel
import com.example.sweetsboutiqueshop.data.repository.dataSource.ProductsApiDataSourse
import com.example.sweetsboutiqueshop.data.repository.dataSource.ProductsDataSourse
import com.example.sweetsboutiqueshop.domain.repository.ProductsCall

class ProductsRepository(
    private val productsApiDataSourse: ProductsApiDataSourse,
    private val productsDataSourse: ProductsDataSourse
) : ProductsCall {

    override fun loadProducts(): LiveData<List<ProductsModel>> {
        return productsDataSourse.loadAllProduct()
    }

    override suspend fun startMigration(context: Context) {
        productsDataSourse.clear()
        productsApiDataSourse.startMigration(context)
    }

}