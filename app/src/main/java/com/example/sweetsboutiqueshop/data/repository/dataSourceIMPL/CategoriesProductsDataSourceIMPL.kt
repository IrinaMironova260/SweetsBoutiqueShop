package com.example.sweetsboutiqueshop.data.repository.dataSourceIMPL

import androidx.lifecycle.LiveData
import com.example.sweetsboutiqueshop.data.localDB.CategoriesProductsDao
import com.example.sweetsboutiqueshop.data.models.CategoriesProductsModel
import com.example.sweetsboutiqueshop.data.repository.dataSource.CategoriesProductsDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoriesProductsDataSourceIMPL (private val dao: CategoriesProductsDao)
    : CategoriesProductsDataSource {

    override fun insert(model: CategoriesProductsModel) {
        CoroutineScope(Dispatchers.IO).launch { dao.insert(model) }
    }

    override fun loadProductsCategory(idProduct: Int): LiveData<List<CategoriesProductsModel>> {
        return dao.loadProductsCategory(idProduct)
    }

    override suspend fun clear() {
        CoroutineScope(Dispatchers.IO).launch { dao.clear() }
    }
}