package com.example.sweetsboutiqueshop.data.repository.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.sweetsboutiqueshop.data.models.ProductsModel
import com.example.sweetsboutiqueshop.data.repository.dataSource.ProductsApiDataSourse
import com.example.sweetsboutiqueshop.data.repository.dataSource.ProductsDataSourse
import com.example.sweetsboutiqueshop.domain.repository.ProductsCall

class ProductsRepository(
    private val productsApiDataSource: ProductsApiDataSourse,
    private val productsDataSource: ProductsDataSourse
) : ProductsCall {

    override fun loadProducts(): LiveData<List<ProductsModel>> {
        return productsDataSource.loadAllProduct()
    }
    override fun loadInfoProduct(idProduct: Int): LiveData<List<ProductsModel>>{
        return productsDataSource.loadInfoProduct(idProduct)
    }

    override fun loadProductsFromCategory(ids:List<Int>): LiveData<List<ProductsModel>> {
        return productsDataSource.loadProductsFromCategory(ids)
    }

    override suspend fun startMigration(context: Context) {
        productsDataSource.clear()
        productsApiDataSource.startMigration(context)
    }

}