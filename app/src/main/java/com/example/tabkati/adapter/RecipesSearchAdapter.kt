package com.example.tabkati.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tabkati.data.ResultsItem
import com.example.tabkati.databinding.RecipeSearchItemBinding

class RecipesSearchAdapter (private val onItemClicked: (ResultsItem) -> Unit) :
    ListAdapter<ResultsItem,
            RecipesSearchAdapter.ItemViewHolder>(DiffCallback) {


    companion object DiffCallback : DiffUtil.ItemCallback<ResultsItem>() {
        override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ResultsItem, newItem: ResultsItem ): Boolean {
            return oldItem.image == newItem.image
        }
    }

    class ItemViewHolder(private var binding: RecipeSearchItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: ResultsItem) {
           binding.data = recipe

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val viewHolder = ItemViewHolder(RecipeSearchItemBinding.inflate(
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
