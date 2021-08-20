package com.example.yemeksiparisapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.appcompat.widget.SearchView
import com.example.yemeksiparisapp.databinding.FragmentHomeBinding
import com.example.yemeksiparisapp.ui.adapters.FoodListAdapter
import com.example.yemeksiparisapp.ui.adapters.RestaurantListAdapter
import com.example.yemeksiparisapp.utils.Resource
import com.facebook.shimmer.ShimmerFrameLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(){

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
        val shimmerFoodContainer = _binding.shimmerFoodContainer
        val shimmerRestContainer = _binding.shimmerRestContainer
        val token = viewModel.getToken()
        setupSearchview()
        if (token != null) {
            setupRestObservable(token,shimmerRestContainer)
            setupFoodsObservable(token,shimmerFoodContainer)
        }
    }

    private fun setupRestObservable(token:String,shimmerRestContainer:ShimmerFrameLayout){
        viewModel.getAllRestaurants(token).observe(viewLifecycleOwner,{
            when(it.status){
                Resource.Status.LOADING -> {
                    shimmerRestContainer.startShimmer()
                }
                Resource.Status.SUCCESS -> {
                    it.data?.let {
                        shimmerRestContainer.stopShimmer()
                        shimmerRestContainer.visibility = View.INVISIBLE
                        restaurantListRvAdapter.restaurantList = it
                        restaurantListRvAdapter.addData()
                    }
                }
                Resource.Status.ERROR -> {
                    //println(it)
                }
            }
        })
    }

    private fun setupFoodsObservable(token:String,shimmerFoodContainer:ShimmerFrameLayout){
        viewModel.getRandomFoods(token).observe(viewLifecycleOwner,{
            when(it.status){
                Resource.Status.LOADING -> {
                    shimmerFoodContainer.startShimmer()
                }
                Resource.Status.SUCCESS -> {
                    it.data?.let{
                        shimmerFoodContainer.stopShimmer()
                        shimmerFoodContainer.visibility = View.GONE
                        foodListRvAdapter.foodList = it
                        foodListRvAdapter.notifyDataSetChanged()

                    }
                }
                Resource.Status.ERROR -> {
                    //println(it)
                }
            }
        })
    }

    private fun setupSearchview(){
        val searchvw = _binding.searchView
        searchvw.setOnQueryTextListener(object:  SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                restaurantListRvAdapter.filter.filter(p0)
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                restaurantListRvAdapter.filter.filter(p0)
                return false
            }
        })
    }

}

