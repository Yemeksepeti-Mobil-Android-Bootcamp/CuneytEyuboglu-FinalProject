package com.example.yemeksiparisapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.yemeksiparisapp.R
import com.example.yemeksiparisapp.databinding.FragmentHomeBinding
import com.example.yemeksiparisapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var _binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()


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
        val token = viewModel.getToken()
        println("benim token: "+token)

        if (token != null) {
            viewModel.getAllRestaurants(token).observe(viewLifecycleOwner,{
                when(it.status){
                    Resource.Status.LOADING -> {
                        println(it)
                    }
                    Resource.Status.SUCCESS -> {
                        println(it)
                    }
                    Resource.Status.ERROR -> {
                        println(it)
                    }
                }
            })
        }
    }

}