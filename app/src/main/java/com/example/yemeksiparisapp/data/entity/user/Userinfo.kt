package com.example.yemeksiparisapp.data.entity.user


import com.example.yemeksiparisapp.data.entity.foods.Randomfoodlist
import com.google.gson.annotations.SerializedName

data class Userinfo(
    @SerializedName("email")
    val email: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("namesurname")
    val namesurname: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("previousOrders")
    val previousOrders: List<Randomfoodlist>,
    @SerializedName("username")
    val username: String,
    @SerializedName("__v")
    val v: Int
)