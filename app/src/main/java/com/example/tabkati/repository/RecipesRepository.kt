package com.example.tabkati.repository

import com.example.tabkati.data.RecipesItem
import com.example.tabkati.data.RecipesRemoteDataSource
import javax.inject.Inject

class RecipesRepository @Inject constructor(private val recipesRemoteDataSource: RecipesRemoteDataSource) {

    suspend fun getRandomRecipes(pages: String="10" ):List<RecipesItem?>? = recipesRemoteDataSource.getRandomRecipes(pages)
    suspend fun getRecipesByCategory(category: String):List<RecipesItem?>? = recipesRemoteDataSource.getRecipesByCategory(category)
    suspend fun getRecipeById(id: String ): RecipesItem? = recipesRemoteDataSource.getRecipeById(id)
    suspend fun searchForRecipes(search: String ):List<RecipesItem?>? = recipesRemoteDataSource.searchForRecipe(search)

}