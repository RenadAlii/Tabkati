package com.example.tabkati.network

import com.example.tabkati.data.Response
import com.example.tabkati.data.SpoonacularRemoteDatasource
import com.example.tabkati.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query




interface RecipeApiService {

    //Get Random list of Recipes.
    @GET("/recipes/random?number=10&apiKey=${API_KEY}")
    suspend fun getRandomRecipes(): SpoonacularRemoteDatasource

    //Get Recipe by id.
    @GET("/recipes/{id}/information?includeNutrition=false&apiKey=${API_KEY}")
    suspend fun getRecipe(@Path("id") RecipeId: String): Response

    //Get Recipes by category.
    @GET("/recipes/random?number=40&tags={category}&apiKey=${API_KEY}")
    suspend fun getRecipesByCategory(@Query("category") category: String): SpoonacularRemoteDatasource

    //Get Recipes by category.
    @GET("/recipes/complexSearch?query={search}&apiKey=${API_KEY}")
    suspend fun searchForRecipes(@Query("search") search: String): SpoonacularRemoteDatasource




}





