package com.example.yemeksiparisapp.data.remote

import com.example.yemeksiparisapp.data.entity.foods.FoodResponse
import com.example.yemeksiparisapp.data.entity.login.LoginRequest
import com.example.yemeksiparisapp.data.entity.login.LoginResponse
import com.example.yemeksiparisapp.data.entity.login.User
import com.example.yemeksiparisapp.data.entity.order.OrderRequest
import com.example.yemeksiparisapp.data.entity.order.OrderResponse
import com.example.yemeksiparisapp.data.entity.register.RegisterRequest
import com.example.yemeksiparisapp.data.entity.register.RegisterResponse
import com.example.yemeksiparisapp.data.entity.restaurants.RestaurantResponse
import com.example.yemeksiparisapp.data.entity.user.UserInfoResponse
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

    @POST("api/user/order")
    suspend fun orderFood(@Header("x-access-token") token:String, @Body order:OrderRequest): Response<OrderResponse>

    @GET("api/user/userinfo")
    suspend fun getUserInfo(@Header("x-access-token") token: String): Response<UserInfoResponse>

    @GET("api/restaurants/searchfood/{food_id}")
    suspend fun getFoodById(@Path("food_id") food_id:String,
                            @Header("x-access-token")token: String) : Response<FoodResponse>
}