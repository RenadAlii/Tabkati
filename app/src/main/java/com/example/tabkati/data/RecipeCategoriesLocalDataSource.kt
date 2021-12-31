package com.example.tabkati.data

import com.example.tabkati.R

data class RecipeCategoriesPictureLocalDataSource(val CategoryImage:Int, val titleOFCat: String, val id: String)


object RecipeCategoriesPictureDataSource {
    val recipeCategoriesPictureList: List<RecipeCategoriesPictureLocalDataSource> = listOf(
        RecipeCategoriesPictureLocalDataSource(
            R.drawable.desert, "Desert","dessert"), RecipeCategoriesPictureLocalDataSource(
            R.drawable.breakfast, "Side Dish","side dish"
        ), RecipeCategoriesPictureLocalDataSource(
            R.drawable.diner, "Main Course","main course"
        ), RecipeCategoriesPictureLocalDataSource(
            R.drawable.dreank, "Drink","drink"

        ), RecipeCategoriesPictureLocalDataSource(
            R.drawable.soap, "Soup","soup"
        ),
        RecipeCategoriesPictureLocalDataSource(
            R.drawable.saletd, "Salad","salad"), RecipeCategoriesPictureLocalDataSource(
            R.drawable.saled2, "Snack","snack"
        ), RecipeCategoriesPictureLocalDataSource(
            R.drawable.pagel, "Breakfast","breakfast"
        ), RecipeCategoriesPictureLocalDataSource(
            R.drawable.lunch, "Vegetarian","vegetarian"
        )

    )
}