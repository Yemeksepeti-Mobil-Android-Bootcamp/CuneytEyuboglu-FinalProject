package com.example.yemeksiparisapp.data.entity.login


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("token")
    val token: String,
    @SerializedName("user")
    val user: User
)