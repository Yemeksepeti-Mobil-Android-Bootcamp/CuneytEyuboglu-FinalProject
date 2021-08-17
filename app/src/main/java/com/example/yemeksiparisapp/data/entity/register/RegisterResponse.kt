package com.example.yemeksiparisapp.data.entity.register


import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("token")
    val token: String,
    @SerializedName("username")
    val username: String
)