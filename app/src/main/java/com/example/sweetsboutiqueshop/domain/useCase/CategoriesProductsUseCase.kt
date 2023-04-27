package com.example.sweetsboutiqueshop.domain.useCase

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.sweetsboutiqueshop.data.models.CategoriesProductsModel
import com.example.sweetsboutiqueshop.domain.repository.CategoriesProductsCall

class CategoriesProductsUseCase (private val call: CategoriesProductsCall) {

    fun loadCategoriesProduct(idProduct:Int): LiveData<List<CategoriesProductsModel>> {
        return call.loadCategoriesProducts(idProduct)
    }

    suspend fun startMigration(context: Context){
        call.startMigration(context)
    }
}