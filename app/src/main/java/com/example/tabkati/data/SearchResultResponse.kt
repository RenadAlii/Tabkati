package com.example.tabkati.data

import com.squareup.moshi.Json

data class SearchResultResponse(

	@Json(name="number")
	val number: Int? = null,

	@Json(name="totalResults")
	val totalResults: Int? = null,

	@Json(name="results")
	val results: List<ResultsItem?>? = null
)

data class ResultsItem(

	@Json(name="image")
	val image: String? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="title")
	val title: String? = null,

)
