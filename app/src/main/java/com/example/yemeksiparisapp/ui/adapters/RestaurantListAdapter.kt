package com.example.yemeksiparisapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yemeksiparisapp.R
import com.example.yemeksiparisapp.data.entity.restaurants.RestaurantResponse
import com.example.yemeksiparisapp.data.entity.restaurants.RestaurantResponseItem
import com.example.yemeksiparisapp.databinding.RowHomerestaurantsBinding
import java.util.*


class RestaurantListAdapter: RecyclerView.Adapter<RestaurantListAdapter.ViewHolder>() , Filterable {

    var restaurantList : RestaurantResponse? = null
    var filteredRestaurantList : RestaurantResponse? = null

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
        println("onbind")
        println(filteredRestaurantList)
        println(restaurantList)
        filteredRestaurantList?.let {
            holder.setItem(it[position])
            holder.binding.restrowCard.setOnClickListener{ View ->
                val bundle = bundleOf("restaurantId" to it[position].id)
                var navController = Navigation.findNavController(View)
                navController.navigate(R.id.action_homeFragment_to_restaurantDetailFragment, bundle)
            }
        }

    }

    override fun getItemCount(): Int {
        filteredRestaurantList?.let {
            return it.size
        }
        return 0
    }

    fun addData() {
        filteredRestaurantList = restaurantList
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    filteredRestaurantList = restaurantList
                } else {
                    val resultList = RestaurantResponse()
                    for (row in restaurantList!!) {
                        println(row.name)
                        if (row.name.lowercase(Locale.ROOT).contains(charSearch.lowercase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    filteredRestaurantList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filteredRestaurantList
                println("filteredlist: "+filteredRestaurantList)
                return filterResults
            }


            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                println(results!!.values)
                if (results.values == null){
                    filteredRestaurantList = RestaurantResponse()
                }  else {
                    results.values as RestaurantResponse
                    println("val: "+results.values)
                    notifyDataSetChanged()
                }
            }
        }
    }

}