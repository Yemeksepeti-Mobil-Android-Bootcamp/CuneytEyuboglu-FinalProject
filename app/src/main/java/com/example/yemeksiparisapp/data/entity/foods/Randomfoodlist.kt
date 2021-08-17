package com.example.yemeksiparisapp.data.entity.foods


import com.google.gson.annotations.SerializedName

data class Randomfoodlist(
    @SerializedName("foodimg")
    val foodimg: String,
    @SerializedName("foodname")
    val foodname: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("price")
    val price: String
)