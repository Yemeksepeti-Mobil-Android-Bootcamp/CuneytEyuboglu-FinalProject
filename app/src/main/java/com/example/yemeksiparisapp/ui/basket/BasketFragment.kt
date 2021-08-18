package com.example.yemeksiparisapp.ui.basket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.yemeksiparisapp.data.entity.restaurants.Foodmenu
import com.example.yemeksiparisapp.databinding.FragmentBasketBinding
import com.example.yemeksiparisapp.ui.adapters.BasketAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketFragment : Fragment() {

    private lateinit var _binding: FragmentBasketBinding
    private val viewModel: BasketViewModel by viewModels()
    private var BasketRvAdapter: BasketAdapter = BasketAdapter(::onRemoveFood)
    var totalPrice:Int=0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBasketBinding.inflate(inflater,container,false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.BasketRecyclerView.adapter = BasketRvAdapter
        viewModel.getBasketFoods()
        viewModel.basketfoodlist.observe(viewLifecycleOwner,{
            println(it)
            it.forEach(){
                totalPrice += it.price.toInt()
            }
            _binding.totalPriceText.text = "$${totalPrice.toString()}"
            BasketRvAdapter.cartList = it
            BasketRvAdapter.notifyDataSetChanged()
        })

    }

    private fun onRemoveFood(item:Foodmenu,allItems:ArrayList<Foodmenu>){
        viewModel.deleteBasketFoodById(item.id)
        totalPrice -= item.price.toInt()
        _binding.totalPriceText.text = "$${totalPrice.toString()}"
    }



}