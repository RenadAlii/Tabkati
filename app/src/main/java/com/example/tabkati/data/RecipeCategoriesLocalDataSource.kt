package com.example.tabkati.data

import com.example.tabkati.R

data class RecipeCategoriesPictureLocalDataSource(val CategoryImage: String)


object RecipeCategoriesPictureDataSource {
    val recipeCategoriesPictureList: List<RecipeCategoriesPictureLocalDataSource> = listOf(
        RecipeCategoriesPictureLocalDataSource(
            R.drawable.desert .toString())
        , RecipeCategoriesPictureLocalDataSource(
            R.drawable.breakfast .toString()
        )
        , RecipeCategoriesPictureLocalDataSource(
            R.drawable.diner .toString()
        ), RecipeCategoriesPictureLocalDataSource(
            R.drawable.dreank .toString()
        )
        , RecipeCategoriesPictureLocalDataSource(
            R.drawable.healthy .toString()
        ), RecipeCategoriesPictureLocalDataSource(
            R.drawable.soap .toString()
        ),
                RecipeCategoriesPictureLocalDataSource(
                R.drawable.saletd .toString())
        , RecipeCategoriesPictureLocalDataSource(
            R.drawable.saled2 .toString()
        )
        , RecipeCategoriesPictureLocalDataSource(
            R.drawable.pasta .toString()
        ), RecipeCategoriesPictureLocalDataSource(
            R.drawable.pagel .toString()
        )
        , RecipeCategoriesPictureLocalDataSource(
            R.drawable.lunch .toString()
        )

    )
}