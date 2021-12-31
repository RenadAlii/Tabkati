package com.example.tabkati.data


import com.example.tabkati.network.RecipeApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class RecipesRemoteDataSource @Inject constructor(private val api: RecipeApiService, private val dispatcher: CoroutineDispatcher=Dispatchers.IO
) {


    suspend fun getRandomRecipes(pages: String):List<RecipesItem?>? =
        withContext(dispatcher){
            api.getRandomRecipes().recipes
        }

    suspend fun getRecipesByCategory(category: String):List<RecipesItem?>? =
        withContext(dispatcher){
            api.getRecipesByCategory(category).recipes
        }

    suspend fun getRecipeById(id: String ):Response =
        withContext(dispatcher){
            api.getRecipe(id)
        }


}