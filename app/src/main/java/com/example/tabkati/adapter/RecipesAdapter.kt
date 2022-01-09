package com.example.tabkati.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tabkati.data.RecipesItemResponse
import com.example.tabkati.databinding.RecipeItemBinding
import com.example.tabkati.ui.recipes.RecipesItemUiState

class RecipesAdapter(private val onItemClicked: (RecipesItemUiState) -> Unit) :
    ListAdapter<RecipesItemUiState,
            RecipesAdapter.ItemViewHolder>(DiffCallback) {


    companion object DiffCallback :
        DiffUtil.ItemCallback<RecipesItemUiState>() {
        override fun areItemsTheSame(
            oldItem: RecipesItemUiState,
            newItem: RecipesItemUiState,
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: RecipesItemUiState,
            newItem: RecipesItemUiState,
        ): Boolean {
            return oldItem.title == newItem.title
        }
    }

    class ItemViewHolder(
        private var binding:
        RecipeItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: RecipesItemUiState) {
            binding.data = recipe

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val viewHolder = ItemViewHolder(
            RecipeItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
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