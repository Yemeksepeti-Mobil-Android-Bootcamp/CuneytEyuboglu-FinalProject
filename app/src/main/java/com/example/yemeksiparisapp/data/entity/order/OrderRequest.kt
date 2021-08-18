package com.example.yemeksiparisapp.data.entity.order

import com.example.yemeksiparisapp.data.entity.restaurants.Foodmenu
import com.google.gson.annotations.SerializedName

data class OrderRequest(
    @SerializedName("order")
    val order:ArrayList<Foodmenu>?
)