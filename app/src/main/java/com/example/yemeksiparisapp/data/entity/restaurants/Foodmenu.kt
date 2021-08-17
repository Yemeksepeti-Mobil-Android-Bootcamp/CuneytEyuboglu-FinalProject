package com.example.yemeksiparisapp.data.entity.restaurants


import com.google.gson.annotations.SerializedName

data class Foodmenu(
    @SerializedName("foodimg")
    val foodimg: String,
    @SerializedName("foodname")
    val foodname: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("price")
    val price: String
)