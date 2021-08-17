package com.example.yemeksiparisapp.data.entity.register

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("namesurname")
    val namesurname: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("username")
    val username:String
)