package com.example.yemeksiparisapp.ui.fooddetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.yemeksiparisapp.data.FoodApiRepository
import com.example.yemeksiparisapp.data.entity.foods.FoodResponse
import com.example.yemeksiparisapp.data.entity.restaurants.Foodmenu
import com.example.yemeksiparisapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FoodDetailViewModel @Inject constructor(
    val foodApiRepository: FoodApiRepository
) : ViewModel() {

    fun getFoodById(foodId:String,token:String): LiveData<Resource<FoodResponse>> {
         return foodApiRepository.getFoodById(foodId,token)
    }
    fun getToken():String?{
        return foodApiRepository.getToken()
    }

    fun addFoodtoBasket(food: Foodmenu){
        foodApiRepository.addFoodtoBasket(food)
    }
}