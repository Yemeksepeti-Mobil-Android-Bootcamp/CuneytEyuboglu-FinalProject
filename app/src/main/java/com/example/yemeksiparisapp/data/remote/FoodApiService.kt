package com.example.yemeksiparisapp.data.remote

import com.example.yemeksiparisapp.data.entity.login.LoginRequest
import com.example.yemeksiparisapp.data.entity.login.LoginResponse
import com.example.yemeksiparisapp.data.entity.register.RegisterRequest
import com.example.yemeksiparisapp.data.entity.register.RegisterResponse
import com.example.yemeksiparisapp.data.entity.restaurants.RestaurantResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface FoodApiService {

    @POST("register")
    suspend fun register(@Body request: RegisterRequest) : Response<RegisterResponse>

    @POST("auth")
    suspend fun login(@Body request: LoginRequest) : Response<LoginResponse>

    @GET("api/restaurants")
    suspend fun getAllRestaurants(@Header("x-access-token") token: String):Response<RestaurantResponse>
}