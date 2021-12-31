package com.example.tabkati.adapter

import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.tabkati.R
import com.example.tabkati.data.RecipesItem

@BindingAdapter("id","imageType")
fun bindImage(imageView: ImageView,id: String?, imageType: String?) {
        val imgUri = " https://spoonacular.com/recipeImages/$id-$imageType".toUri().buildUpon().scheme("https").build()
    imageView.load(imgUri){
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)

    }
}

//@BindingAdapter("listData")
//fun bindRecyclerView(recyclerView: RecyclerView, data: List<RecipesItem>?){
//    val adapter= recyclerView.adapter as RecipesAdapter
//    // this tell the RecyclerView new list is available.
//    adapter.submitList(data)
//}
