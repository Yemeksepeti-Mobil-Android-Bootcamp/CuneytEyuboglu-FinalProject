package com.example.yemeksiparisapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yemeksiparisapp.data.entity.foods.FoodResponse
import com.example.yemeksiparisapp.data.entity.foods.Randomfoodlist
import com.example.yemeksiparisapp.data.entity.restaurants.RestaurantResponse
import com.example.yemeksiparisapp.data.entity.restaurants.RestaurantResponseItem
import com.example.yemeksiparisapp.databinding.RowHomefoodsBinding
import com.example.yemeksiparisapp.databinding.RowHomerestaurantsBinding


class FoodListAdapter: RecyclerView.Adapter<FoodListAdapter.ViewHolder>() {

    var foodList : FoodResponse? = null

    class ViewHolder(var binding: RowHomefoodsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setItem(item: Randomfoodlist) {
            Glide.with(binding.root.context).load(item.foodimg).into(binding.imageView2)
            binding.textView4.text = item.foodname
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = RowHomefoodsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        foodList?.let {
            holder.setItem(it.randomfoodlist[position])
        }
    }

    override fun getItemCount(): Int {
        foodList?.let {
            return it.randomfoodlist.size
        }
        return 0
    }
}