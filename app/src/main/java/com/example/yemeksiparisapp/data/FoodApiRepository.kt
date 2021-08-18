package com.example.yemeksiparisapp.data

import com.example.yemeksiparisapp.data.entity.login.LoginRequest
import com.example.yemeksiparisapp.data.entity.register.RegisterRequest
import com.example.yemeksiparisapp.data.entity.restaurants.Foodmenu
import com.example.yemeksiparisapp.data.local.LocaleDataBaseSource
import com.example.yemeksiparisapp.data.local.LocaleDataSource
import com.example.yemeksiparisapp.data.remote.RemoteDataSource
import com.example.yemeksiparisapp.utils.performAuthTokenNetworkOperation
import com.example.yemeksiparisapp.utils.performNetworkOperation
import javax.inject.Inject

class FoodApiRepository @Inject constructor(
    private var remoteDataSource: RemoteDataSource,
    private var localeDataSource: LocaleDataSource,
    private var localeDataBaseSource: LocaleDataBaseSource
) {
    fun login(loginRequest: LoginRequest) = performAuthTokenNetworkOperation(
        call = { remoteDataSource.login(loginRequest) },
        save = { localeDataSource.saveToken(it)}
    )

    fun register(registerRequest: RegisterRequest) = performNetworkOperation {
        remoteDataSource.register(registerRequest)
    }

    fun saveToken(token:String) = localeDataSource.saveToken(token)

    fun getToken(): String? {
        return localeDataSource.getToken()
    }


    fun signOut() = localeDataSource.saveToken("")

    fun getAllRestaurants(token: String) = performNetworkOperation {
        remoteDataSource.getAllRestaurants(token)
    }

    fun getRandomFoods(token: String) = performNetworkOperation {
        remoteDataSource.getRandomFoods(token)
    }

    fun getRestaurantbyId(restaurant_id:String,token: String) = performNetworkOperation {
        remoteDataSource.getRestaurantbyId(restaurant_id,token)
    }

    fun removeBasketFood(foodId:String) = localeDataBaseSource.removeBasketFood(foodId)

    fun getAllBasketFoods() = localeDataBaseSource.getAllBasketFoods()

    fun addFoodtoBasket(food:Foodmenu) = localeDataBaseSource.addFoodtoBasket(food)
}