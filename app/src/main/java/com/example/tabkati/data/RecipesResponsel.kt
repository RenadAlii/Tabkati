package com.example.tabkati.data

import android.provider.MediaStore
import com.example.tabkati.domain.models.RecipesModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


data class SpoonacularContainer(

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


	@Json(name="steps")
	val steps: List<StepsItem?>? = null
)



data class RecipesItem(

	@Json(name="instructions")
	val instructions: String? = null,

	@Json(name="analyzedInstructions")
	val analyzedInstructions: List<AnalyzedInstructionsItem?>? = null,

	@Json(name="glutenFree")
	val glutenFree: Boolean? = null,


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
	val extendedIngredients: List<ExtendedIngredientsItem?>? = null,


	@Json(name="weightWatcherSmartPoints")
	val weightWatcherSmartPoints: Int? = null,

	@Json(name="spoonacularSourceUrl")
	val spoonacularSourceUrl: String? = null
)


data class StepsItem(

	@Json(name="number")
	val number: Int? = null,

	@Json(name="ingredients")
	val ingredients: List<IngredientsItem?>? = null,

	@Json(name="step")
	val step: String? = null,

	@Json(name="length")
	val length: Length? = null
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


	@Json(name="meta")
	val meta: List<String?>? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="originalString")
	val originalString: String? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="metaInformation")
	val metaInformation: List<String?>? = null
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
