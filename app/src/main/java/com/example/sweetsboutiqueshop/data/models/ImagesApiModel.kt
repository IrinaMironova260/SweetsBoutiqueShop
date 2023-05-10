package com.example.sweetsboutiqueshop.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ImagesApiModel (

    @SerializedName("id") @Expose
    var id : Int? = null,

    @SerializedName("url") @Expose
    var url : String? = null
)

