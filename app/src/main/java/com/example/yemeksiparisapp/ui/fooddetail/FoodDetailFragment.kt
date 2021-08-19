package com.example.yemeksiparisapp.ui.fooddetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.yemeksiparisapp.databinding.FragmentFoodDetailBinding
import com.example.yemeksiparisapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FoodDetailFragment : Fragment() {

    private lateinit var _binding : FragmentFoodDetailBinding
    private val viewModel : FoodDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFoodDetailBinding.inflate(inflater,container,false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val foodItemId = arguments?.getString("foodItemId")
        println(foodItemId)
        val token = viewModel.getToken()
        if(token!=null && foodItemId!=null) {
            viewModel.getFoodById(foodItemId,token).observe(viewLifecycleOwner,{
                when(it.status){
                    Resource.Status.LOADING -> {}
                    Resource.Status.SUCCESS -> {
                        it.data?.randomfoodlist?.let{
                            _binding.foodDetailName.text = it[0].foodname
                            Glide.with(_binding.root.context).load(it[0].foodimg).into(_binding.foodDetailimg)
                        }
                    }
                    Resource.Status.ERROR -> {}

                }
            })
        }
    }

}