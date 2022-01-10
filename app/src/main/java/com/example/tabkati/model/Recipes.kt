package com.example.tabkati.model

import com.example.tabkati.data.AnalyzedInstructionsItemResponse
import com.example.tabkati.data.ExtendedIngredientsItemResponse

data class RecipesModel(
    val id: Int?,
    val title: String?,
    val image: String?,
    val servings: Int?,
    val aggregateLikes: Int?,
    val readyInMinutes: Int?,
    val sourceUrl: String? = null,
    val ingredients: List<ExtendedIngredientsItemResponse?>?,
    val steps: List<AnalyzedInstructionsItemResponse?>?,
    val dairyFree: Boolean?,
    val vegetarian: Boolean?,
    val veryHealthy: Boolean?,
    val glutenFree: Boolean?,
    val instructions: String?)
