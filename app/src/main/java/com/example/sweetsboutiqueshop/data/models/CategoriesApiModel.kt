package com.example.sweetsboutiqueshop.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CategoriesApiModel (
    @SerializedName("id") @Expose
    var id: Int? = null,
    @SerializedName("name") @Expose
    var name: String? = null,
    @SerializedName("image") @Expose
    var image: String? = null,
    @SerializedName("description") @Expose
    var description: String? = null,
    @SerializedName("parent_cat") @Expose
    var parentCat: Int? = null,
    @SerializedName("child_cat") @Expose
    var childCat: Int? = null
)