package com.example.tabkati.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tabkati.data.ExtendedIngredientsItem
import com.example.tabkati.databinding.IngredientsItemBinding


class IngredientsAdapter(private val onItemClicked: (ExtendedIngredientsItem) -> Unit) :
    ListAdapter<ExtendedIngredientsItem,
            IngredientsAdapter.ItemViewHolder>(DiffCallback) {


    companion object DiffCallback :
        DiffUtil.ItemCallback<ExtendedIngredientsItem>() {
        override fun areItemsTheSame(oldItem: ExtendedIngredientsItem,
            newItem: ExtendedIngredientsItem,
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ExtendedIngredientsItem, newItem: ExtendedIngredientsItem): Boolean {
            return oldItem.nameClean == newItem.nameClean
        }
    }

    class ItemViewHolder(private var binding: IngredientsItemBinding ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(ingredient: ExtendedIngredientsItem) {
            binding.data = ingredient

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val viewHolder =
            ItemViewHolder(IngredientsItemBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false))
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }
        return viewHolder
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}
