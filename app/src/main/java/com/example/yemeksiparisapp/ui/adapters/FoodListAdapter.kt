package com.example.yemeksiparisapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yemeksiparisapp.R
import com.example.yemeksiparisapp.data.entity.foods.FoodResponse
import com.example.yemeksiparisapp.data.entity.restaurants.Foodmenu
import com.example.yemeksiparisapp.databinding.RowHomefoodsBinding


class FoodListAdapter: RecyclerView.Adapter<FoodListAdapter.ViewHolder>() {

    var foodList : FoodResponse? = null

    class ViewHolder(var binding: RowHomefoodsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setItem(item: Foodmenu) {
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
            holder.binding.imageView2.setOnClickListener{ View ->
                val bundle = bundleOf("foodItemId" to it.randomfoodlist[position].id)
                var navController = Navigation.findNavController(View)
                navController.navigate(R.id.action_homeFragment_to_foodDetailFragment, bundle)

            }
        }
    }

    override fun getItemCount(): Int {
        foodList?.let {
            return it.randomfoodlist.size
        }
        return 0
    }
}