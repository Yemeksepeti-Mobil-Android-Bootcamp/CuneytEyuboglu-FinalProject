package com.example.yemeksiparisapp.data.entity.order

import com.google.gson.annotations.SerializedName

data class OrderResponse(
    @SerializedName("status")
    var status:String
)