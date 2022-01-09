package com.example.tabkati.ui.recipes

import com.example.tabkati.data.IngredientsItem
import com.squareup.moshi.Json



data class StepsUiState(
val steps: List<StepsItemUiState?>? = listOf(),

)

data class StepsItemUiState(
    val number: Int? = null,
    val ingredients: List<IngredientsItemUiState?>? = listOf(),
    val step: String? = null
)



data class IngredientsItemUiState(
    val originalString: String? = null,
)



//data class RecipesUiState(
//    val recipesItems: List<RecipesItemUiState> = listOf())
data class RecipesUiState(
    val id: Int? = null,
    val instructions: String? = null,
    val glutenFree: Boolean? = null,
    val healthScore: Double? = null,
    val title: String? = null,
    val diets: List<String?>? = null,
    val aggregateLikes: Int? = null,
    val readyInMinutes: Int? = null,
    val sourceUrl: String? = null,
    val dairyFree: Boolean? = null,
    val servings: Int? = null,
    val vegetarian: Boolean? = null,
    val image: String? = null,
    val veryHealthy: Boolean? = null,
    val vegan: Boolean? = null)