package com.example.tabkati.adapter

import android.util.Log
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.tabkati.R
import com.example.tabkati.data.RecipesItem

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
fun bindRecyclerView(recyclerView: RecyclerView, data: List<RecipesItem>?){
    val adapter= recyclerView.adapter as RecipesAdapter
    // this tell the RecyclerView new list is available.
    adapter.submitList(data)
}
