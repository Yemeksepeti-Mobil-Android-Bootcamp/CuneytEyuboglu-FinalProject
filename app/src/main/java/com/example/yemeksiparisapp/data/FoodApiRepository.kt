package com.example.yemeksiparisapp.data

import com.example.yemeksiparisapp.data.entity.login.LoginRequest
import com.example.yemeksiparisapp.data.entity.register.RegisterRequest
import com.example.yemeksiparisapp.data.entity.restaurants.RestaurantRequest
import com.example.yemeksiparisapp.data.local.LocaleDataSource
import com.example.yemeksiparisapp.data.remote.RemoteDataSource
import com.example.yemeksiparisapp.utils.performAuthTokenNetworkOperation
import com.example.yemeksiparisapp.utils.performNetworkOperation
import javax.inject.Inject

class FoodApiRepository @Inject constructor(
    private var remoteDataSource: RemoteDataSource,
    private var localeDataSource: LocaleDataSource
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
}