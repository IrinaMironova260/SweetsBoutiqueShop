package com.example.sweetsboutiqueshop.data.api

import com.example.sweetsboutiqueshop.data.models.*
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("loadCategories.php")
    fun loadCategories(): Call<ArrayList<CategoriesApiModel>>

    @GET("loadProducts.php")
    fun loadProducts(): Call<ArrayList<ProductsApiModel>>

    @GET("loadCategoriesProducts.php")
    fun loadCatigoriesProducts(): Call<ArrayList<CategoriesProductsApiModel>>

    @GET("loadImageProduct.php")
    fun loadImageProducts(): Call<ArrayList<ImagesProductsApiModel>>

    @GET("loadImage.php")
    fun loadImage(): Call<ArrayList<ImagesApiModel>>
}
