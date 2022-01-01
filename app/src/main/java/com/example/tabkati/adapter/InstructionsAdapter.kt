package com.example.tabkati.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tabkati.data.AnalyzedInstructionsItem
import com.example.tabkati.data.ExtendedIngredientsItem
import com.example.tabkati.data.RecipesItem
import com.example.tabkati.data.StepsItem
import com.example.tabkati.databinding.IngredientsItemBinding
import com.example.tabkati.databinding.InstructionsItemBinding

class InstructionsAdapter (private val onItemClicked: (StepsItem) -> Unit) :
    ListAdapter<StepsItem,
            InstructionsAdapter.ItemViewHolder>(DiffCallback) {


    companion object DiffCallback :
        DiffUtil.ItemCallback<StepsItem>() {
        override fun areItemsTheSame(oldItem: StepsItem,
                                     newItem: StepsItem,
        ): Boolean {
            return oldItem.number == newItem.number
        }

        override fun areContentsTheSame(oldItem: StepsItem, newItem: StepsItem): Boolean {
            return oldItem.step == newItem.step
        }
    }

    class ItemViewHolder(private var binding: InstructionsItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind( Step: StepsItem) {
            binding.data =  Step
            //binding.dataIng =  Step.ingredients


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
