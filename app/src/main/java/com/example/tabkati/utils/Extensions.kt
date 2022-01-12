package com.example.tabkati.utils

import android.view.View
import android.widget.ImageView

enum class BookMark {
    FILL, EMPTY
}
fun ImageView.isFill(imageViewFill: ImageView,imageViewEmpty: ImageView,bookMark: BookMark){

    when(bookMark){
        BookMark.EMPTY ->{
            imageViewEmpty.visibility = View.GONE
            imageViewFill.visibility = View.VISIBLE

        }
        BookMark.FILL ->{
            imageViewFill.visibility = View.GONE
            imageViewEmpty.visibility = View.VISIBLE
        }
    }


}