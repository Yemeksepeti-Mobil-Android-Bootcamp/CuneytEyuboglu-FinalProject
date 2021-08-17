package com.example.yemeksiparisapp.data.entity.foods


import com.google.gson.annotations.SerializedName

data class FoodResponse(
    @SerializedName("randomfoodlist")
    val randomfoodlist: List<Randomfoodlist>,
    @SerializedName("status")
    val status: String
)