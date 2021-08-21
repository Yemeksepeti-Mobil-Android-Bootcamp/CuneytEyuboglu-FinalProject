package com.example.yemeksiparisapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.yemeksiparisapp.data.FoodApiRepository
import com.example.yemeksiparisapp.data.entity.login.LoginRequest
import com.example.yemeksiparisapp.data.entity.login.LoginResponse
import com.example.yemeksiparisapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    var foodApiRepository: FoodApiRepository
) : ViewModel(){

    fun login(userIdentity: String, password: String): LiveData<Resource<LoginResponse>> {
        if (userIdentity.contains('@')){
            val request = LoginRequest(null,userIdentity, password)
            return foodApiRepository.login(request)
        }else{
            val request = LoginRequest(userIdentity,null, password)
            return foodApiRepository.login(request)
        }
    }
}