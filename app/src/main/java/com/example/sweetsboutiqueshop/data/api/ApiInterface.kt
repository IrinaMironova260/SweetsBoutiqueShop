package com.example.sweetsboutiqueshop.data.api

import com.example.sweetsboutiqueshop.data.models.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

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

    @GET("loadAboutCompany.php")
    fun loadAboutCompany(): Call<ArrayList<AboutUsApiModel>>

    @FormUrlEncoded
    @POST("createOrder.php")
    fun createOrder(
        @Field("name") name: String?,
        @Field("phone") phone: String?,
        @Field("desc_order") descOrder: String?
    ): Call<ResponseBody?>?
}
