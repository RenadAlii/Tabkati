package com.example.tabkati.adapter


import com.example.tabkati.databinding.GridViewRecipeCateItemBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tabkati.ui.recipes.CategoryUIState

class RecipeCategoriesAdapter(private val onItemClicked: (CategoryUIState) -> Unit) :
    ListAdapter<CategoryUIState,
            RecipeCategoriesAdapter.ItemViewHolder>(DiffCallback) {


    companion object DiffCallback :
        DiffUtil.ItemCallback<CategoryUIState>() {
        override fun areItemsTheSame(
            oldItem: CategoryUIState,
            newItem: CategoryUIState,
        ): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: CategoryUIState,
            newItem: CategoryUIState,
        ): Boolean {
            return oldItem.image == newItem.image
        }
    }

    class ItemViewHolder(
        private var binding: GridViewRecipeCateItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(category: CategoryUIState) {
            binding.catImage.setImageResource(category.image.toInt())
            binding.data = category

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
