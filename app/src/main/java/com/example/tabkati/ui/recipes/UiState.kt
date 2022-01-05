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