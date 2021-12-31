package com.example.tabkati.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tabkati.data.RecipeCategoriesPictureLocalDataSource
import com.example.tabkati.data.RecipesItem
import com.example.tabkati.databinding.FragmentHomeBinding
import com.example.tabkati.databinding.GridViewRecipeCateItemBinding
import com.example.tabkati.databinding.RecipeItemBinding

class RecipesAdapter(private val onItemClicked: (RecipesItem) -> Unit) :
    ListAdapter<RecipesItem,
            RecipesAdapter.ItemViewHolder>(DiffCallback) {


    companion object DiffCallback :
        DiffUtil.ItemCallback<RecipesItem>() {
        override fun areItemsTheSame(
            oldItem: RecipesItem,
            newItem: RecipesItem,
        ): Boolean {
            return oldItem.originalId == newItem.originalId
        }

        override fun areContentsTheSame(
            oldItem: RecipesItem,
            newItem: RecipesItem,
        ): Boolean {
            return oldItem.title == newItem.title
        }
    }

    class ItemViewHolder(
        private var binding:
        RecipeItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: RecipesItem) {
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
