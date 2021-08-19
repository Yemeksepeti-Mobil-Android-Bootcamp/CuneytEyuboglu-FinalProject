package com.example.yemeksiparisapp.data.entity.foods


import com.example.yemeksiparisapp.data.entity.restaurants.Foodmenu
import com.google.gson.annotations.SerializedName

data class FoodResponse(
    @SerializedName("randomfoodlist")
    val randomfoodlist: List<Foodmenu>,
    @SerializedName("status")
    val status: String
)