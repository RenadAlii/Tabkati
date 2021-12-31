package com.example.tabkati.adapter

import android.annotation.SuppressLint
import com.example.tabkati.data.RecipeCategoriesPictureLocalDataSource
import com.example.tabkati.databinding.GridViewRecipeCateItemBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class RecipeCategoriesAdapter(private val onItemClicked: (RecipeCategoriesPictureLocalDataSource) -> Unit) :
    ListAdapter<RecipeCategoriesPictureLocalDataSource,
            RecipeCategoriesAdapter.ItemViewHolder>(DiffCallback) {


    companion object DiffCallback :
        DiffUtil.ItemCallback<RecipeCategoriesPictureLocalDataSource>() {
        override fun areItemsTheSame(
            oldItem: RecipeCategoriesPictureLocalDataSource,
            newItem: RecipeCategoriesPictureLocalDataSource,
        ): Boolean {
            return oldItem.titleOFCat == newItem.titleOFCat
        }

        override fun areContentsTheSame(
            oldItem: RecipeCategoriesPictureLocalDataSource,
            newItem: RecipeCategoriesPictureLocalDataSource,
        ): Boolean {
            return oldItem.CategoryImage == newItem.CategoryImage
        }
    }

    class ItemViewHolder(
        private var binding: GridViewRecipeCateItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(category: RecipeCategoriesPictureLocalDataSource) {
            binding.catImage.setImageResource(category.CategoryImage.toInt())
            binding.recipeCatTitle.text = category.titleOFCat

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ItemViewHolder {
        val viewHolder = ItemViewHolder(
            GridViewRecipeCateItemBinding.inflate(
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
