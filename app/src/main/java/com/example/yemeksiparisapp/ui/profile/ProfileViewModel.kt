package com.example.yemeksiparisapp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.yemeksiparisapp.data.FoodApiRepository
import com.example.yemeksiparisapp.data.entity.order.OrderResponse
import com.example.yemeksiparisapp.data.entity.user.UserInfoResponse
import com.example.yemeksiparisapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    var foodApiRepository: FoodApiRepository
): ViewModel(){

    fun getToken():String?{
        return foodApiRepository.getToken()
    }

    fun getUserInfo(token:String):LiveData<Resource<UserInfoResponse>>{
        return foodApiRepository.getUserInfo(token)
    }

    fun signOut(){
        foodApiRepository.signOut()
    }

    fun clearOrders(token:String):LiveData<Resource<OrderResponse>>{
        return foodApiRepository.clearOrders(token)
    }


}