package com.example.tabkati.adapter

import android.graphics.Paint
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.airbnb.lottie.LottieAnimationView
import com.example.tabkati.R
import com.example.tabkati.data.ExtendedIngredientsItemResponse
import com.example.tabkati.data.RecipesItemResponse
import com.example.tabkati.data.ResultsItem
import com.example.tabkati.ui.recipes.CategoryUIState
import com.example.tabkati.ui.recipes.RecipesItemUiState
import com.example.tabkati.utils.RecipesApiStatus

@BindingAdapter("imageUrl")
fun AppCompatImageView.bindImage( imageUrl: String?) {
    imageUrl?.let {
        val imgUri = "$imageUrl".toUri().buildUpon().scheme("https").build()
        Log.e("renad", "bindImage: $imgUri", )
        this.load(imgUri){
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
fun bindIngredientsRecyclerView(recyclerView: RecyclerView, data: List<ExtendedIngredientsItemResponse>?){
    val adapter= recyclerView.adapter as IngredientsAdapter
    // this tell the RecyclerView new list is available.
    adapter.submitList(data)
}
@BindingAdapter("recipeApiStatus")
fun bindStatus(statusImageView: LottieAnimationView,
               status: RecipesApiStatus?) {
    when (status) {
        RecipesApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setAnimation("57429-dots-animation.json")
        }
        RecipesApiStatus.ERROR ->{
            statusImageView.visibility = View.VISIBLE
            statusImageView.setAnimation("12978-error.json")
        }
        RecipesApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("recipeSearchStatus")
fun bindSearchStatus(statusImageView: LottieAnimationView,
               status: RecipesApiStatus?) {
    when (status) {
        RecipesApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setAnimation("28952-seacrching.json")
        }
        RecipesApiStatus.ERROR ->{
            statusImageView.visibility = View.VISIBLE
            statusImageView.setAnimation("12978-error.json")
        }
        RecipesApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}





@BindingAdapter("listOFSearch")
fun bindSearchRecyclerView(recyclerView: RecyclerView, data: List<ResultsItem>?){
    val adapter= recyclerView.adapter as RecipesSearchAdapter
    // this tell the RecyclerView new list is available.
    adapter.submitList(data)
}


//@BindingAdapter("app:items")
//fun setItems(listView: RecyclerView, items: List<Task>?) {
//    items?.let {
//        (listView.adapter as TasksAdapter).submitList(items)
//    }
//}

@BindingAdapter("app:completedTask")
fun setStyle(textView: TextView, enabled: Boolean) {
    if (enabled) {
        textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    } else {
        textView.paintFlags = textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
}