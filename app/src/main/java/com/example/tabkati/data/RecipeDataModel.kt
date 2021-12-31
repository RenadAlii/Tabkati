package com.example.tabkati.data

import com.squareup.moshi.Json

data class Response(

	@Json(name="instructions")
	val instructions: String? = null,

	@Json(name="sustainable")
	val sustainable: Boolean? = null,

	@Json(name="analyzedInstructions")
	val analyzedInstructions: List<Any?>? = null,

	@Json(name="glutenFree")
	val glutenFree: Boolean? = null,

	@Json(name="veryPopular")
	val veryPopular: Boolean? = null,

	@Json(name="healthScore")
	val healthScore: Double? = null,

	@Json(name="title")
	val title: String? = null,

	@Json(name="diets")
	val diets: List<Any?>? = null,

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

data class Us(

	@Json(name="amount")
	val amount: Double? = null,

	@Json(name="unitShort")
	val unitShort: String? = null,

	@Json(name="unitLong")
	val unitLong: String? = null
)

data class Measures(

	@Json(name="metric")
	val metric: Metric? = null,

	@Json(name="us")
	val us: Us? = null
)


data class Metric(

	@Json(name="amount")
	val amount: Double? = null,

	@Json(name="unitShort")
	val unitShort: String? = null,

	@Json(name="unitLong")
	val unitLong: String? = null
)

data class ExtendedIngredientsItem(

	@Json(name="image")
	val image: String? = null,

	@Json(name="amount")
	val amount: Double? = null,

	@Json(name="nameClean")
	val nameClean: String? = null,

	@Json(name="original")
	val original: String? = null,

	@Json(name="aisle")
	val aisle: String? = null,

	@Json(name="consistency")
	val consistency: String? = null,

	@Json(name="originalName")
	val originalName: String? = null,

	@Json(name="unit")
	val unit: String? = null,

	@Json(name="measures")
	val measures: Measures? = null,

	@Json(name="meta")
	val meta: List<Any?>? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="originalString")
	val originalString: String? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="metaInformation")
	val metaInformation: List<Any?>? = null
)
