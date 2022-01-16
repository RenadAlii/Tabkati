package com.example.tabkati.utils

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView

enum class BookMark {
    FILL, EMPTY
}

fun ImageView.isFill(imageViewFill: ImageView, imageViewEmpty: ImageView, bookMark: BookMark) {

    when (bookMark) {
        BookMark.EMPTY -> {
            imageViewEmpty.visibility = View.GONE
            imageViewFill.visibility = View.VISIBLE

        }
        BookMark.FILL -> {
            imageViewFill.visibility = View.GONE
            imageViewEmpty.visibility = View.VISIBLE
        }
    }


}

fun LottieAnimationView.lottieAnimationStatus(
   prograss: LottieAnimationView, error: LottieAnimationView,
    status: RecipesApiStatus?,recyclerView: RecyclerView
) {
    when (status) {
        RecipesApiStatus.LOADING -> {
            prograss.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
            error.visibility = View.GONE
        }
        RecipesApiStatus.ERROR -> {
            error.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
            prograss.visibility = View.GONE
        }
        RecipesApiStatus.DONE -> {
            error.visibility = View.GONE
            prograss.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE

        }
    }
}
fun ProgressBar.progressBarStatus(
    prograssBar: ProgressBar,recyclerView: RecyclerView
   , status: RecipesApiStatus,
) {
    when (status) {
        RecipesApiStatus.LOADING -> {
            prograssBar.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE

        }
        RecipesApiStatus.ERROR -> {
            prograssBar.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE

        }
        RecipesApiStatus.DONE -> {
            prograssBar.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE

        }
    }
}
