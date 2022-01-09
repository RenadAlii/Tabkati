package com.example.tabkati.domain.models

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.example.tabkati.data.AnalyzedInstructionsItem
import com.example.tabkati.data.ExtendedIngredientsItem

data class RecipesModel(
    val id: Int?,
    val title: String?,
    val image: String?,
    val servings: Int?,
    val aggregateLikes: Int?,
    val readyInMinutes: Int?,
    val sourceUrl: String? = null,
    val ingredients: List<ExtendedIngredientsItem?>?,
    val steps: List<AnalyzedInstructionsItem?>?,
    val dairyFree: Boolean?,
    val vegetarian: Boolean?,
    val veryHealthy: Boolean?,
    val glutenFree: Boolean?,
    val instructions: String?)
