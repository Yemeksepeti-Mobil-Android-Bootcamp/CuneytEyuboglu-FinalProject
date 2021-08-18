package com.example.yemeksiparisapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yemeksiparisapp.R
import com.example.yemeksiparisapp.data.entity.restaurants.Foodmenu
import com.example.yemeksiparisapp.data.local.SqLiteDbManager
import com.example.yemeksiparisapp.databinding.RowRestdetailfoodsBinding

class RestaurantDetailAdapter(private val onAddCallback: (Foodmenu) -> Unit): RecyclerView.Adapter<RestaurantDetailAdapter.ViewHolder>() {

    var foodList : List<Foodmenu>? = null

    class ViewHolder(var binding: RowRestdetailfoodsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setItem(item: Foodmenu) {
            Glide.with(binding.root.context).load(item.foodimg).into(binding.imageView2)
            binding.restDetailFoodPrice.text = "$${item.price}"
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
            holder.binding.btnAddtoCart.setOnClickListener{View ->
                onAddCallback(it[position])
                var navController = Navigation.findNavController(View)
                navController.navigate(R.id.action_restaurantDetailFragment_to_basketFragment)
            }
        }
    }

    override fun getItemCount(): Int {
        foodList?.let {
            return it.size
        }
        return 0
    }
}