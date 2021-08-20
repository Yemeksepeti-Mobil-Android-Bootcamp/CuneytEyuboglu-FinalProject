package com.example.yemeksiparisapp.ui.restaurantdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.yemeksiparisapp.R
import com.example.yemeksiparisapp.data.entity.restaurants.Foodmenu
import com.example.yemeksiparisapp.databinding.FragmentLoginBinding
import com.example.yemeksiparisapp.databinding.FragmentRestaurantDetailBinding
import com.example.yemeksiparisapp.ui.adapters.FoodListAdapter
import com.example.yemeksiparisapp.ui.adapters.RestaurantDetailAdapter
import com.example.yemeksiparisapp.ui.adapters.RestaurantListAdapter
import com.example.yemeksiparisapp.ui.login.LoginViewModel
import com.example.yemeksiparisapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantDetailFragment : Fragment() {

    private lateinit var _binding : FragmentRestaurantDetailBinding
    private val viewModel : RestaurantViewModel by viewModels()
    private var restaurantDetailAdapter: RestaurantDetailAdapter = RestaurantDetailAdapter(::onAddFood)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRestaurantDetailBinding.inflate(inflater,container,false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val restaurant_id = arguments?.getString("restaurantId")
        val token = viewModel.getToken()
        _binding.restaurantDetailFoods.adapter = restaurantDetailAdapter
        if (restaurant_id != null && token != null) {
            viewModel.getRestaurantById(restaurant_id,token).observe(viewLifecycleOwner,{
                when(it.status){
                    Resource.Status.LOADING -> { }
                    Resource.Status.SUCCESS -> {
                        it.data?.let {
                            _binding.restaurantDetailProgressBar.visibility = View.GONE
                            _binding.restaurantDetailName.text = it[0].name
                            _binding.restaurantDetailDesc.text = it[0].description
                            Glide.with(_binding.root.context).load(it[0].restaurantimgurl).into(_binding.restaurantDetailimg)
                            restaurantDetailAdapter.foodList = it[0].foodmenu
                            restaurantDetailAdapter.notifyDataSetChanged()
                        }
                    }
                    Resource.Status.ERROR -> {}
                }
            })

        }

    }
    private fun onAddFood(item: Foodmenu){
        viewModel.addFoodtoBasket(item)
    }

}