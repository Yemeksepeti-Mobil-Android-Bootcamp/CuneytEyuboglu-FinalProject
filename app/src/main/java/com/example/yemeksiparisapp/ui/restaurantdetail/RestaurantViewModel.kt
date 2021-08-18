package com.example.yemeksiparisapp.ui.restaurantdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.yemeksiparisapp.data.FoodApiRepository
import com.example.yemeksiparisapp.data.entity.restaurants.Foodmenu
import com.example.yemeksiparisapp.data.entity.restaurants.RestaurantResponse
import com.example.yemeksiparisapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel@Inject constructor(
    val foodApiRepository: FoodApiRepository
) : ViewModel(){

    fun getRestaurantById(restaurant_id:String,token:String): LiveData<Resource<RestaurantResponse>> {
        return foodApiRepository.getRestaurantbyId(restaurant_id,token)
    }
    fun getToken():String?{
        return foodApiRepository.getToken()
    }

    fun addFoodtoBasket(food:Foodmenu){
        foodApiRepository.addFoodtoBasket(food)
    }
}