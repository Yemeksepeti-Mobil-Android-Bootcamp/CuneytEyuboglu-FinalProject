package com.example.yemeksiparisapp.data.entity.restaurants


import com.google.gson.annotations.SerializedName

data class RestaurantResponseItem(
    @SerializedName("description")
    val description: String,
    @SerializedName("foodmenu")
    val foodmenu: List<Foodmenu>,
    @SerializedName("_id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("restaurantimgurl")
    val restaurantimgurl: String,
    @SerializedName("__v")
    val v: Int
)