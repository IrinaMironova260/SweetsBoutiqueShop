package com.example.sweetsboutiqueshop.domain.useCase

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.sweetsboutiqueshop.data.models.ProductsModel
import com.example.sweetsboutiqueshop.domain.repository.ProductsCall

class ProductsUseCase (private val call: ProductsCall) {

    fun loadProducts(): LiveData<List<ProductsModel>> {
        return call.loadProducts()
    }

    fun loadInfoProduct(idProduct:Int): LiveData<List<ProductsModel>> {
        return call.loadInfoProduct(idProduct)
    }

    fun loadProductsFromCategory(ids:List<Int>): LiveData<List<ProductsModel>> {
        return call.loadProductsFromCategory(ids)
    }

    suspend fun startMigration(context: Context){
        call.startMigration(context)
    }
}