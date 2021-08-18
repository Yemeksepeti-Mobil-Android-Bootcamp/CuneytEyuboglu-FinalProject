package com.example.yemeksiparisapp.data.local

import com.example.yemeksiparisapp.data.entity.restaurants.Foodmenu
import javax.inject.Inject

class LocaleDataBaseSource @Inject constructor(
    val sqLiteDbManager: SqLiteDbManager
) {


    fun removeBasketFood(foodId:String){
        sqLiteDbManager.deleteBasketFoodById(foodId)
    }
    fun getAllBasketFoods() : ArrayList<Foodmenu> {
        return sqLiteDbManager.getAllBasketFoods()
    }

    fun addFoodtoBasket(food:Foodmenu){
        return sqLiteDbManager.addFoodtoBasket(food)
    }

    fun emptyBasket(){
        sqLiteDbManager.emptyBasket()
    }


}