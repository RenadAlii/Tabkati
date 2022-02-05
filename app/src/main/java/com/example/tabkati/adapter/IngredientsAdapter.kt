package com.example.tabkati.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tabkati.databinding.IngredientsItemBinding
import com.example.tabkati.ui.recipes.ExtendedIngredientsItemUiState


class IngredientsAdapter(private val onItemClicked: (ExtendedIngredientsItemUiState) -> Unit) :
    ListAdapter<ExtendedIngredientsItemUiState,
            IngredientsAdapter.ItemViewHolder>(DiffCallback) {


    companion object DiffCallback :
        DiffUtil.ItemCallback<ExtendedIngredientsItemUiState>() {
        override fun areItemsTheSame(oldItem: ExtendedIngredientsItemUiState,
                                     newItem: ExtendedIngredientsItemUiState,
        ): Boolean {
            return oldItem.amount == newItem.amount
        }

        override fun areContentsTheSame(oldItem: ExtendedIngredientsItemUiState, newItem: ExtendedIngredientsItemUiState): Boolean {
            return oldItem.nameClean == newItem.nameClean
        }
    }

    class ItemViewHolder(private var binding: IngredientsItemBinding ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(ingredient: ExtendedIngredientsItemUiState) {
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