package com.example.sweetsboutiqueshop.data.repository.dataSourceIMPL

import android.content.Context
import android.widget.Toast
import com.example.sweetsboutiqueshop.data.api.ApiClient
import com.example.sweetsboutiqueshop.data.models.ProductsApiModel
import com.example.sweetsboutiqueshop.data.models.ProductsModel
import com.example.sweetsboutiqueshop.data.repository.dataSource.ProductsApiDataSourse
import com.example.sweetsboutiqueshop.data.repository.dataSource.ProductsDataSourse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsApiDataSourseIMPL(private val productsDataSource: ProductsDataSourse):
    ProductsApiDataSourse {

    override fun startMigration (context: Context) {

        val call = ApiClient.instance?.api?.loadProducts()
        call?.enqueue(object: Callback<ArrayList<ProductsApiModel>> {
            override fun onResponse(
                call: Call<ArrayList<ProductsApiModel>>,
                response: Response<ArrayList<ProductsApiModel>>
            ) {

                var loadProducts: ArrayList<ProductsApiModel>? = null
                loadProducts?.clear()
                loadProducts = (response.body() as ArrayList<ProductsApiModel>?)!!

                for (audit in loadProducts) {
                    audit.id?.let {
                        ProductsModel(
                            it,
                            audit.name.toString(),
                            audit.mainImage.toString(),
                            audit.price!!,
                            audit.salePrice!!,
                            audit.description.toString(),
                            audit.deliveryTime.toString()
                        )
                    }?.let {
                        productsDataSource.insert(
                            it
                        )
                    }
                }
                Toast.makeText(context, "ЗАГРУЗКА", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<ArrayList<ProductsApiModel>>, t: Throwable) {
                Toast.makeText(context, "ОШИБКА! ВКЛЮЧИТЕ ИНТЕРНЕТ!", Toast.LENGTH_SHORT).show()
            }
        })
    }

}