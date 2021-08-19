package com.example.yemeksiparisapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yemeksiparisapp.data.entity.foods.Randomfoodlist
import com.example.yemeksiparisapp.databinding.RowHomefoodsBinding
import java.util.*


class PreviousOrdersAdapter: RecyclerView.Adapter<PreviousOrdersAdapter.ViewHolder>() {

    var orderList : List<Randomfoodlist>? = null

    class ViewHolder(var binding: RowHomefoodsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setItem(item: Randomfoodlist) {
            Glide.with(binding.root.context).load(item.foodimg).into(binding.imageView2)
            binding.textView4.text = item.foodname
            binding.textView5.text = item.price
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = RowHomefoodsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        orderList?.let {
            if (it[position] != null){
                holder.setItem(it[position])
            }
        }
    }

    override fun getItemCount(): Int {
        orderList?.let {
            return it.size
        }
        return 0
    }
}