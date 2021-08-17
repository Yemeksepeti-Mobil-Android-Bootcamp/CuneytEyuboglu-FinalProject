package com.example.yemeksiparisapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.yemeksiparisapp.R
import com.example.yemeksiparisapp.databinding.FragmentHomeBinding
import com.example.yemeksiparisapp.ui.adapters.FoodListAdapter
import com.example.yemeksiparisapp.ui.adapters.RestaurantListAdapter
import com.example.yemeksiparisapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var _binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private var restaurantListRvAdapter: RestaurantListAdapter = RestaurantListAdapter()
    private var foodListRvAdapter: FoodListAdapter = FoodListAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.RestaurantsRecyclerView.adapter = restaurantListRvAdapter
        _binding.FoodsRecyclerView.adapter = foodListRvAdapter
        val token = viewModel.getToken()
        println("benim token: "+token)

        if (token != null) {
            viewModel.getAllRestaurants(token).observe(viewLifecycleOwner,{
                when(it.status){
                    Resource.Status.LOADING -> {
                        println(it)
                    }
                    Resource.Status.SUCCESS -> {
                        it.data?.let {
                            restaurantListRvAdapter.restaurantList = it
                            restaurantListRvAdapter.notifyDataSetChanged()
                        }
                    }
                    Resource.Status.ERROR -> {
                        println(it)
                    }
                }
            })
            viewModel.getRandomFoods(token).observe(viewLifecycleOwner,{
                when(it.status){
                    Resource.Status.LOADING -> {
                        println(it)
                    }
                    Resource.Status.SUCCESS -> {
                        it.data?.let{
                            foodListRvAdapter.foodList = it
                            foodListRvAdapter.notifyDataSetChanged()

                        }
                    }
                    Resource.Status.ERROR -> {
                        println(it)
                    }
                }
            })
        }
    }

}