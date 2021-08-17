package com.example.yemeksiparisapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.yemeksiparisapp.data.FoodApiRepository
import com.example.yemeksiparisapp.data.entity.restaurants.RestaurantResponse
import com.example.yemeksiparisapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    var foodApiRepository: FoodApiRepository
): ViewModel()  {

    fun getAllRestaurants(token:String) : LiveData<Resource<RestaurantResponse>> = foodApiRepository.getAllRestaurants(token)

    fun getToken() : String? = foodApiRepository.getToken()
}