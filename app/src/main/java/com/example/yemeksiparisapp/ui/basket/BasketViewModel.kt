package com.example.yemeksiparisapp.ui.basket

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemeksiparisapp.data.FoodApiRepository
import com.example.yemeksiparisapp.data.entity.restaurants.Foodmenu
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BasketViewModel@Inject constructor(
    var foodApiRepository: FoodApiRepository
): ViewModel(){

    val basketfoodlist:MutableLiveData<ArrayList<Foodmenu>> = MutableLiveData()

    fun getBasketFoods(){
        basketfoodlist.postValue(foodApiRepository.getAllBasketFoods())
    }

    fun deleteBasketFoodById(foodId:String){
        foodApiRepository.removeBasketFood(foodId)
    }
}