package com.example.yemeksiparisapp.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val foodApiService: FoodApiService
) {
}