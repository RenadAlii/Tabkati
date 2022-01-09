package com.example.tabkati.data

import com.example.tabkati.domain.models.RecipesModel
import com.squareup.moshi.Json


data class SpoonacularContainer(

	@Json(name="recipes")
	val recipes: List<RecipesItemResponse?>?
)
data class Length(

	@Json(name="number")
	val number: Int? ,

	@Json(name="unit")
	val unit: String?
)


data class AnalyzedInstructionsItem(


	@Json(name="steps")
	val steps: List<StepsItemResponse?>? = null
)



data class RecipesItem(

	@Json(name="instructions")
	val instructions: String? = null,

	@Json(name="analyzedInstructions")
	val analyzedInstructions: List<AnalyzedInstructionsItemResponse?>?,

	@Json(name="glutenFree")
	val glutenFree: Boolean?,


	@Json(name="healthScore")
	val healthScore: Double? = null,

	@Json(name="title")
	val title: String? = null,

	@Json(name="diets")
	val diets: List<String?>? = null,

	@Json(name="aggregateLikes")
	val aggregateLikes: Int? = null,


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


	@Json(name="extendedIngredients")
	val extendedIngredients: List<ExtendedIngredientsItemResponse?>?,


	@Json(name="weightWatcherSmartPoints")
	val weightWatcherSmartPoints: Int? = null,

	@Json(name="spoonacularSourceUrl")
	val spoonacularSourceUrl: String? = null
)


data class StepsItem(

	@Json(name="number")
	val number: Int? = null,

	@Json(name="ingredients")
	val ingredients: List<IngredientsItemResponse?>? = null,

	@Json(name="step")
	val step: String? = null,

	@Json(name="length")
	val length: LengthResponse? = null
)

data class ExtendedIngredientsItem(

	@Json(name="image")
	val image: String? ,

	@Json(name="amount")
	val amount: Double?  ,
	@Json(name="nameClean")
	val nameClean: String? ,

	@Json(name="original")
	val original: String? ,

	@Json(name="aisle")
	val aisle: String? ,

	@Json(name="consistency")
	val consistency: String? ,

	@Json(name="originalName")
	val originalName: String? ,

	@Json(name="unit")
	val unit: String? ,


	@Json(name="meta")
	val meta: List<String>,

	@Json(name="name")
	val name: String? ,

	@Json(name="originalString")
	val originalString: String? ,

	@Json(name="id")
	val id: Int? ,

	@Json(name="metaInformation")
	val metaInformation: List<String?>?
)

data class IngredientsItem(

	@Json(name="image")
	val image: String? ,

	@Json(name="localizedName")
	val localizedName: String? ,

	@Json(name="name")
	val name: String? ,

	@Json(name="id")
	val id: Int?
)



// converts from data transfer objects"response" to database objects.
fun SpoonacularContainer.asDatabaseModel(): Array<RecipesModel>? {
	return recipes?.map {
		RecipesModel(
			id = it?.id,
			title = it?.title,
			image = it?.image,
			servings = it?.servings,
			aggregateLikes = it?.aggregateLikes,
			readyInMinutes = it?.readyInMinutes,
			sourceUrl = it?.sourceUrl,
			dairyFree = it?.dairyFree,
			vegetarian = it?.vegetarian,
			veryHealthy = it?.veryHealthy,
			glutenFree = it?.glutenFree,
			ingredients = it?.extendedIngredients,
			steps = it?.analyzedInstructions,
			instructions = it?.instructions)
	}?.toTypedArray()
}

