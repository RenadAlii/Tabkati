package com.example.tabkati.adapter


import androidx.appcompat.widget.AppCompatImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.tabkati.R
import com.example.tabkati.data.ResultsItem
import com.example.tabkati.ui.recipes.ExtendedIngredientsItemUiState
import com.example.tabkati.ui.recipes.RecipesItemUiState



@BindingAdapter("imageUrl")
fun AppCompatImageView.bindImage( imageUrl: String?) {
    if (imageUrl.isNullOrEmpty()){
        this.load(R.drawable.loading_animation)
    }
    imageUrl?.let {
        val imgUri = "$imageUrl".toUri().buildUpon().scheme("https").build()
            this.load(imgUri) {
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_broken_image)

            }
        }
    }



@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<RecipesItemUiState>?){
    val adapter= recyclerView.adapter as RecipesAdapter
    // this tell the RecyclerView new list is available.
    adapter.submitList(data)
}

@BindingAdapter("listIngredients")
fun bindIngredientsRecyclerView(recyclerView: RecyclerView, data: List<ExtendedIngredientsItemUiState>?){
    val adapter= recyclerView.adapter as IngredientsAdapter
    // this tell the RecyclerView new list is available.
    adapter.submitList(data)
}

@BindingAdapter("listOFSearch")
fun bindSearchRecyclerView(recyclerView: RecyclerView, data: List<ResultsItem>?){
    val adapter= recyclerView.adapter as RecipesSearchAdapter
    // this tell the RecyclerView new list is available.
    adapter.submitList(data)
}


