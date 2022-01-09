package com.example.tabkati.data

import com.squareup.moshi.Json

data class SearchResultResponse(

	@Json(name="number")
	val number: Int,

	@Json(name="totalResults")
	val totalResults: Int,

	@Json(name="results")
	val results: List<ResultsItem>
)

data class ResultsItem(

	@Json(name="image")
	val image: String,

	@Json(name="id")
	val id: Int,

	@Json(name="title")
	val title: String,

)
