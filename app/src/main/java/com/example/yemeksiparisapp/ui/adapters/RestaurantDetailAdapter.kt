package com.example.yemeksiparisapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yemeksiparisapp.data.entity.foods.Randomfoodlist
import com.example.yemeksiparisapp.data.entity.restaurants.Foodmenu
import com.example.yemeksiparisapp.databinding.RowRestdetailfoodsBinding

class RestaurantDetailAdapter: RecyclerView.Adapter<RestaurantDetailAdapter.ViewHolder>() {

    var foodList : List<Foodmenu>? = null

    class ViewHolder(var binding: RowRestdetailfoodsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setItem(item: Foodmenu) {
            Glide.with(binding.root.context).load(item.foodimg).into(binding.imageView2)
            binding.textView4.text = item.foodname
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = RowRestdetailfoodsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        foodList?.let {
            holder.setItem(it[position])
        }
    }

    override fun getItemCount(): Int {
        foodList?.let {
            return it.size
        }
        return 0
    }
}