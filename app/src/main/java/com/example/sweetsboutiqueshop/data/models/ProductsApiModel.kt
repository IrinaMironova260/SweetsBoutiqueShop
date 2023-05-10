package com.example.sweetsboutiqueshop.data.models
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProductsApiModel (
    @SerializedName("id") @Expose
    var id: Int? = null,
    @SerializedName("name") @Expose
    var name: String? = null,
    @SerializedName("main_image") @Expose
    var mainImage: String? = null,
    @SerializedName("price") @Expose
    var price: Int? = null,
    @SerializedName("sale_price") @Expose
    var salePrice: Int? = null,
    @SerializedName("description") @Expose
    var description: String? = null,
    @SerializedName("delivery_time") @Expose
    var deliveryTime: String? = null
)