package com.example.yemeksiparisapp.ui.basket

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.HandlerCompat.postDelayed
import androidx.fragment.app.viewModels
import androidx.lifecycle.switchMap
import com.example.yemeksiparisapp.R
import com.example.yemeksiparisapp.data.entity.order.OrderRequest
import com.example.yemeksiparisapp.data.entity.restaurants.Foodmenu
import com.example.yemeksiparisapp.databinding.FragmentBasketBinding
import com.example.yemeksiparisapp.ui.adapters.BasketAdapter
import com.example.yemeksiparisapp.utils.Resource
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
        val token = viewModel.getToken()
        _binding.BasketRecyclerView.adapter = BasketRvAdapter
        viewModel.getBasketFoods()
        viewModel.basketfoodlist.observe(viewLifecycleOwner,{
            it.forEach(){
                totalPrice += it.price.toInt()
            }
            _binding.totalPriceText.text = "$${totalPrice.toString()}"
            BasketRvAdapter.cartList = it
            BasketRvAdapter.notifyDataSetChanged()
        })
        _binding.orderbutton.setOnClickListener{
            popupHandler()
            token?.let {
                var orderedFoods = OrderRequest(viewModel.basketfoodlist.value)
                viewModel.orderFood(it,orderedFoods).observe(viewLifecycleOwner,{
                    when(it.status){
                        Resource.Status.LOADING -> {}
                        Resource.Status.SUCCESS -> {
                            it.data?.let{
                                if (it.status == "success"){
                                    viewModel.emptyBasket()
                                    viewModel.getBasketFoods()
                                }
                            }
                        }
                        Resource.Status.ERROR -> {}
                    }
                })

            }
        }

    }

    private fun onRemoveFood(item:Foodmenu,allItems:ArrayList<Foodmenu>){
        viewModel.deleteBasketFoodById(item.id)
        totalPrice -= item.price.toInt()
        _binding.totalPriceText.text = "$${totalPrice.toString()}"
    }

    private fun popupHandler(){
        val popup = Dialog(requireContext())
        popup.setContentView(R.layout.popup_layout)
        popup.show()
        postDelayed(Handler(),{popup.dismiss()},"popup",5000)
    }

}