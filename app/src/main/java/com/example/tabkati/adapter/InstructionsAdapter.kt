package com.example.tabkati.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tabkati.databinding.InstructionsItemBinding
import com.example.tabkati.ui.recipes.StepsItemsUiState

class InstructionsAdapter(private val onItemClicked: (StepsItemsUiState) -> Unit) :
    ListAdapter<StepsItemsUiState,
            InstructionsAdapter.ItemViewHolder>(DiffCallback) {



    companion object DiffCallback :
        DiffUtil.ItemCallback<StepsItemsUiState>() {
        override fun areItemsTheSame(
            oldItem: StepsItemsUiState,
            newItem: StepsItemsUiState,
        ): Boolean {
            return oldItem.number == newItem.number
        }

        override fun areContentsTheSame(
            oldItem: StepsItemsUiState,
            newItem: StepsItemsUiState,
        ): Boolean {
            return oldItem.step == newItem.step
        }
    }

    class ItemViewHolder(private var binding: InstructionsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(Step: StepsItemsUiState) {
            binding.data = Step
        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val viewHolder =
            ItemViewHolder(InstructionsItemBinding.inflate(LayoutInflater.from(parent.context),
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
