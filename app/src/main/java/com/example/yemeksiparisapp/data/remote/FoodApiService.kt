package com.example.yemeksiparisapp.data.remote

import com.example.yemeksiparisapp.data.entity.foods.FoodResponse
import com.example.yemeksiparisapp.data.entity.login.LoginRequest
import com.example.yemeksiparisapp.data.entity.login.LoginResponse
import com.example.yemeksiparisapp.data.entity.register.RegisterRequest
import com.example.yemeksiparisapp.data.entity.register.RegisterResponse
import com.example.yemeksiparisapp.data.entity.restaurants.RestaurantResponse
import retrofit2.Response
import retrofit2.http.*

interface FoodApiService {

    @POST("register")
    suspend fun register(@Body request: RegisterRequest) : Response<RegisterResponse>

    @POST("auth")
    suspend fun login(@Body request: LoginRequest) : Response<LoginResponse>

    @GET("api/restaurants")
    suspend fun getAllRestaurants(@Header("x-access-token") token: String): Response<RestaurantResponse>

    @GET("api/restaurants/randomfood")
    suspend fun getRandomFoods(@Header("x-access-token") token: String): Response<FoodResponse>

    @GET("api/restaurants/{restaurant_id}")
    suspend fun getRestaurantbyId(@Path("restaurant_id") restaurant_id : String,
                                  @Header("x-access-token")token: String): Response<RestaurantResponse>
}