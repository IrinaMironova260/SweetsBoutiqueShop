package com.example.sweetsboutiqueshop.data.repository.dataSourceIMPL

import androidx.lifecycle.LiveData
import com.example.sweetsboutiqueshop.data.localDB.ProductsDao
import com.example.sweetsboutiqueshop.data.models.ProductsModel
import com.example.sweetsboutiqueshop.data.repository.dataSource.ProductsDataSourse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductsDataSourseIMPL(private val dao: ProductsDao): ProductsDataSourse {

    override fun insert(productsModel: ProductsModel) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.insert(productsModel)
        }
    }

    override fun loadAllProduct(): LiveData<List<ProductsModel>> {
        return dao.getAllProduct()
    }

    override suspend fun clear() {
        CoroutineScope(Dispatchers.IO).launch { dao.clear() }
    }

}