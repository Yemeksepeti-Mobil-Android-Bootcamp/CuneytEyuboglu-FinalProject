package com.example.yemeksiparisapp.data.remote

import com.example.yemeksiparisapp.data.entity.login.LoginRequest
import com.example.yemeksiparisapp.data.entity.order.OrderRequest
import com.example.yemeksiparisapp.data.entity.register.RegisterRequest
import com.example.yemeksiparisapp.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val foodApiService: FoodApiService
) : BaseDataSource() {

    suspend fun login(request: LoginRequest) = getResult{foodApiService.login(request)}

    suspend fun register(request: RegisterRequest) = getResult { foodApiService.register(request) }

    suspend fun getAllRestaurants(token: String) = getResult { foodApiService.getAllRestaurants(token) }

    suspend fun getRandomFoods(token: String) = getResult { foodApiService.getRandomFoods(token) }

    suspend fun getRestaurantbyId(restaurant_id:String,token: String) = getResult { foodApiService.getRestaurantbyId(restaurant_id,token) }

    suspend fun orderFood(token: String, order:OrderRequest) = getResult { foodApiService.orderFood(token,order) }

    suspend fun getUserInfo(token: String) = getResult { foodApiService.getUserInfo(token) }

    suspend fun getFoodById(foodId:String,token:String)= getResult { foodApiService.getFoodById(foodId,token) }

    suspend fun clearOrders(token: String) = getResult { foodApiService.clearOrders(token) }


}