package com.example.yemeksiparisapp.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.yemeksiparisapp.data.FoodApiRepository
import com.example.yemeksiparisapp.data.entity.register.RegisterRequest
import com.example.yemeksiparisapp.data.entity.register.RegisterResponse
import com.example.yemeksiparisapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val foodApiRepository: FoodApiRepository
) : ViewModel() {

    fun register(
        namesurname: String,
        email: String,
        password: String,
        username: String
    ): LiveData<Resource<RegisterResponse>> {
        val request = RegisterRequest(email, namesurname, password, username)
        return foodApiRepository.register(request)
    }

    fun saveToken(token:String) {
        foodApiRepository.saveToken(token)
    }

}