package com.example.yemeksiparisapp.data.remote

import com.example.yemeksiparisapp.data.entity.login.LoginRequest
import com.example.yemeksiparisapp.data.entity.register.RegisterRequest
import com.example.yemeksiparisapp.data.entity.restaurants.RestaurantRequest
import com.example.yemeksiparisapp.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val foodApiService: FoodApiService
) : BaseDataSource() {

    suspend fun login(request: LoginRequest) = getResult{foodApiService.login(request)}

    suspend fun register(request: RegisterRequest) = getResult { foodApiService.register(request) }

    suspend fun getAllRestaurants(token: String) = getResult { foodApiService.getAllRestaurants(token) }

    suspend fun getRandomFoods(token: String) = getResult { foodApiService.getRandomFoods(token) }
}