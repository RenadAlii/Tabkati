package com.example.tabkati.data

import com.example.tabkati.R

data class RecipeCategoriesPictureLocalDataSource(val CategoryImage: String, val titleOFCat: String)


object RecipeCategoriesPictureDataSource {
    val recipeCategoriesPictureList: List<RecipeCategoriesPictureLocalDataSource> = listOf(
        RecipeCategoriesPictureLocalDataSource(
            R.drawable.desert.toString(), "Desert"), RecipeCategoriesPictureLocalDataSource(
            R.drawable.breakfast.toString(), "Side Dish "
        ), RecipeCategoriesPictureLocalDataSource(
            R.drawable.diner.toString(), "Main Course"
        ), RecipeCategoriesPictureLocalDataSource(
            R.drawable.dreank.toString(), "Drink"

        ), RecipeCategoriesPictureLocalDataSource(
            R.drawable.soap.toString(), "Soup"
        ),
        RecipeCategoriesPictureLocalDataSource(
            R.drawable.saletd.toString(), "Salad"), RecipeCategoriesPictureLocalDataSource(
            R.drawable.saled2.toString(), "Snack"
        ), RecipeCategoriesPictureLocalDataSource(
            R.drawable.pagel.toString(), "Breakfast"
        ), RecipeCategoriesPictureLocalDataSource(
            R.drawable.lunch.toString(), "Vegetarian"
        )

    )
}