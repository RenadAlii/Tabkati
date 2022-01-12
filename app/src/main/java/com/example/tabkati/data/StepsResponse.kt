package com.example.tabkati.data

import com.squareup.moshi.Json

//data class StepsResponse(
//
//	@Json(name="StepsResponse")
//	val stepsResponse: List<StepsResponseItem?>? = null
//)

data class StepsItem(

	@Json(name="number")
	val number: Int? = null,

	@Json(name="step")
	val step: String? = null,

	@Json(name="ingredients")
	val ingredients: List<Any?>? = null


)

data class StepsResponseItem(
	@Json(name="steps")
	val steps: List<StepsItem?>? = null
)



data class IngredientsItem(

	@Json(name="image")
	val image: String? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="id")
	val id: Int? = null
)
