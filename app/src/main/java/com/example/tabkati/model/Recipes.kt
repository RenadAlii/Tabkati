package com.example.tabkati.model



data class RecipesModel(
    val id: Int?=null,
    val title: String?=null,
    val image: String?=null,
    val servings: Int?=null,
    val aggregateLikes: Int?=null,
    val readyInMinutes: Int?=null,
    val sourceUrl: String? = null,
    val ingredients: List<ExtendedIngredientsItem>?= listOf(),
    val steps: List<StepsItems>?= listOf(),
    val instructions: String?=null
  )

data class StepsItems(
    val number: Int? = 0,
    val step: String? = "",
    val ingredients: List<StepIngredients?>? = listOf(),
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