package com.example.yemeksiparisapp.data.entity.user


import com.google.gson.annotations.SerializedName

data class UserInfoResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("userinfo")
    val userinfo: Userinfo
)