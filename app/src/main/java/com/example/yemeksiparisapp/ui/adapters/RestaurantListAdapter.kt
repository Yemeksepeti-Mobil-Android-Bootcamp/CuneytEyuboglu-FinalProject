package com.example.yemeksiparisapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yemeksiparisapp.R
import com.example.yemeksiparisapp.data.entity.restaurants.RestaurantResponse
import com.example.yemeksiparisapp.data.entity.restaurants.RestaurantResponseItem
import com.example.yemeksiparisapp.databinding.RowHomerestaurantsBinding


class RestaurantListAdapter: RecyclerView.Adapter<RestaurantListAdapter.ViewHolder>() {

    var restaurantList : RestaurantResponse? = null

    class ViewHolder(var binding: RowHomerestaurantsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setItem(item: RestaurantResponseItem) {
            Glide.with(binding.root.context).load(item.restaurantimgurl).into(binding.rowRestaurantImg)
            binding.rowRestaurantName.text = item.name
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = RowHomerestaurantsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        restaurantList?.let {
            holder.setItem(it[position])
            holder.binding.restrowCard.setOnClickListener{ View ->
                val bundle = bundleOf("restaurantId" to it[position].id)
                var navController = Navigation.findNavController(View)
                navController.navigate(R.id.action_homeFragment_to_restaurantDetailFragment, bundle)
            }
        }

    }

    override fun getItemCount(): Int {
        restaurantList?.let {
            return it.size
        }
        return 0
    }
}