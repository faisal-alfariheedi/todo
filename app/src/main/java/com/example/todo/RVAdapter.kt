package com.example.todo

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.databinding.RvlistBinding

class RVAdapter(private val rv: ArrayList<items>): RecyclerView.Adapter<RVAdapter.ItemViewHolder>() {
    class ItemViewHolder(val binding: RvlistBinding): RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        return ItemViewHolder(
            RvlistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val rvv = rv[position].item
        val rvvd= rv[position].chek
        holder.binding.apply {
            tod.text = rvv
            done.isChecked = rvvd
            done.setOnCheckedChangeListener { CompoundButton, _ ->
                rv[position].chek=done.isChecked

            }

        }
    }

    override fun getItemCount() = rv.size

}