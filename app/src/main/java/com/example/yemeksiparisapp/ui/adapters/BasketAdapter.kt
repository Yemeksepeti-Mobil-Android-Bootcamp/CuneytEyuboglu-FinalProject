package com.example.yemeksiparisapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yemeksiparisapp.data.entity.restaurants.Foodmenu
import com.example.yemeksiparisapp.databinding.RowBasketfoodBinding

class BasketAdapter(private val onDeleteCallback: (Foodmenu,ArrayList<Foodmenu>) -> Unit): RecyclerView.Adapter<BasketAdapter.ViewHolder>()  {

    var cartList : ArrayList<Foodmenu>? = null
    class ViewHolder(var binding: RowBasketfoodBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setItem(item: Foodmenu) {
            Glide.with(binding.root.context).load(item.foodimg).into(binding.BasketFoodimg)
            binding.BasketFoodName.text = item.foodname
            binding.BasketFoodPrice.text = "$${item.price}"
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = RowBasketfoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        cartList?.let {
            holder.setItem(it[position])
            holder.binding.btnRemovefromCart.setOnClickListener{View ->
                onDeleteCallback(it[position],it)
                it.remove(it[position])
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, it.size)
            }
        }
    }

    override fun getItemCount(): Int {
        cartList?.let{
            return it.size
        }
        return 0
    }
}