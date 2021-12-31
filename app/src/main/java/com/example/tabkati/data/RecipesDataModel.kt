package com.example.tabkati.data

import com.squareup.moshi.Json

data class SpoonacularRemoteDatasource(

	@Json(name="recipes")
	val recipes: List<RecipesItem?>? = null
)

data class Length(

	@Json(name="number")
	val number: Int? = null,

	@Json(name="unit")
	val unit: String? = null
)

data class AnalyzedInstructionsItem(

	@Json(name="name")
	val name: String? = null,

	@Json(name="steps")
	val steps: List<StepsItem?>? = null
)

data class EquipmentItem(

	@Json(name="image")
	val image: String? = null,

	@Json(name="localizedName")
	val localizedName: String? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="id")
	val id: Int? = null
)



data class RecipesItem(

	@Json(name="instructions")
	val instructions: String? = null,

	@Json(name="sustainable")
	val sustainable: Boolean? = null,

	@Json(name="analyzedInstructions")
	val analyzedInstructions: List<AnalyzedInstructionsItem?>? = null,

	@Json(name="glutenFree")
	val glutenFree: Boolean? = null,

	@Json(name="veryPopular")
	val veryPopular: Boolean? = null,

	@Json(name="healthScore")
	val healthScore: Double? = null,

	@Json(name="title")
	val title: String? = null,

	@Json(name="diets")
	val diets: List<String?>? = null,

	@Json(name="aggregateLikes")
	val aggregateLikes: Int? = null,

	@Json(name="creditsText")
	val creditsText: String? = null,

	@Json(name="readyInMinutes")
	val readyInMinutes: Int? = null,

	@Json(name="sourceUrl")
	val sourceUrl: String? = null,

	@Json(name="dairyFree")
	val dairyFree: Boolean? = null,

	@Json(name="servings")
	val servings: Int? = null,

	@Json(name="vegetarian")
	val vegetarian: Boolean? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="imageType")
	val imageType: String? = null,

	@Json(name="summary")
	val summary: String? = null,

	@Json(name="image")
	val image: String? = null,

	@Json(name="veryHealthy")
	val veryHealthy: Boolean? = null,

	@Json(name="vegan")
	val vegan: Boolean? = null,

	@Json(name="cheap")
	val cheap: Boolean? = null,

	@Json(name="extendedIngredients")
	val extendedIngredients: List<ExtendedIngredientsItem?>? = null,

	@Json(name="dishTypes")
	val dishTypes: List<String?>? = null,

	@Json(name="gaps")
	val gaps: String? = null,

	@Json(name="cuisines")
	val cuisines: List<Any?>? = null,

	@Json(name="lowFodmap")
	val lowFodmap: Boolean? = null,

	@Json(name="license")
	val license: String? = null,

	@Json(name="weightWatcherSmartPoints")
	val weightWatcherSmartPoints: Int? = null,

	@Json(name="occasions")
	val occasions: List<Any?>? = null,

	@Json(name="spoonacularScore")
	val spoonacularScore: Double? = null,

	@Json(name="pricePerServing")
	val pricePerServing: Double? = null,

	@Json(name="sourceName")
	val sourceName: String? = null,

	@Json(name="originalId")
	val originalId: Any? = null,

	@Json(name="spoonacularSourceUrl")
	val spoonacularSourceUrl: String? = null
)



data class StepsItem(

	@Json(name="number")
	val number: Int? = null,

	@Json(name="ingredients")
	val ingredients: List<IngredientsItem?>? = null,

	@Json(name="equipment")
	val equipment: List<Any?>? = null,

	@Json(name="step")
	val step: String? = null,

	@Json(name="length")
	val length: Length? = null
)



data class IngredientsItem(

	@Json(name="image")
	val image: String? = null,

	@Json(name="localizedName")
	val localizedName: String? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="id")
	val id: Int? = null
)

