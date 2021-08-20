package com.example.yemeksiparisapp.ui.basket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemeksiparisapp.data.FoodApiRepository
import com.example.yemeksiparisapp.data.entity.order.OrderRequest
import com.example.yemeksiparisapp.data.entity.order.OrderResponse
import com.example.yemeksiparisapp.data.entity.restaurants.Foodmenu
import com.example.yemeksiparisapp.utils.Resource
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

    fun emptyBasket(){
        foodApiRepository.emptyBasket()
    }

    fun getToken() : String?{
        return foodApiRepository.getToken()
    }

    fun orderFood(token:String, order: OrderRequest) : LiveData<Resource<OrderResponse>> {
        return foodApiRepository.orderFood(token,order)
    }

}