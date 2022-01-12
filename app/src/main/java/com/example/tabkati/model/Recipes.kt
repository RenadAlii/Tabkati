package com.example.tabkati.model

import com.example.tabkati.data.AnalyzedInstructionsItemResponse
import com.example.tabkati.data.ExtendedIngredientsItemResponse
import com.example.tabkati.ui.recipes.ingredientsUiState

data class RecipesModel(
    val id: Int?,
    val title: String?,
    val image: String?,
    val servings: Int?,
    val aggregateLikes: Int?,
    val readyInMinutes: Int?,
    val sourceUrl: String? = null,
    val ingredients: List<ExtendedIngredientsItem?>?,
    val steps: List<StepsItems?>?,
    val instructions: String?
  )

data class StepsItems(
    val number: Int = 0,
    val step: String = "",
    val ingredients: List<StepIngredients> = listOf(),
)

data class ExtendedIngredientsItem(
    val nameClean: String? =null,
    val amount: Double? = null,
    val unit: String? = null,
)

data class StepIngredients(

    val image: String? = null,
    val name: String? = null
)