package com.example.tabkati.network

import com.example.tabkati.data.RecipesItemResponse
import com.example.tabkati.data.SearchResultResponse
import com.example.tabkati.data.SpoonacularRemoteDatasource
import com.example.tabkati.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query







interface RecipeApiService {

    //Get Random list of Recipes.
    @GET("/recipes/random?number=10&apiKey=${API_KEY}")
    suspend fun getRandomRecipes():  SpoonacularRemoteDatasource

    //Get Recipe by id.
    @GET("/recipes/{id}/information?includeNutrition=false&apiKey=${API_KEY}")
    suspend fun getRecipe(@Path("id") RecipeId: String): RecipesItemResponse

    //Get Recipes by category.
    @GET("/recipes/random?")
    suspend fun getRecipesByCategory(
        @Query("number") pages: String="20"
        ,@Query("tags") category: String
        ,@Query("apiKey") apiKey: String=API_KEY
    ):SpoonacularRemoteDatasource
    //search for Recipes.
    @GET("/recipes/complexSearch?")
    suspend fun searchForRecipes(@Query("query") query: String
                                 ,@Query("apiKey") apiKey: String=API_KEY
    ): SearchResultResponse


    //Get Recipe steps by id.
    @GET("/recipes/{id}/analyzedInstructions?stepBreakdown=true&apiKey=${API_KEY}")
    suspend fun getRecipeSteps(@Path("id") RecipeId: String): RecipesItemResponse


}


