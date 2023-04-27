package com.example.sweetsboutiqueshop.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CategoriesProductsApiModel (
    @SerializedName("id") @Expose
    var id: Int? = null,

    @SerializedName("product_id") @Expose
    var productId : Int? = null,

    @SerializedName("category_id") @Expose
    var categoryId: Int? = null

)