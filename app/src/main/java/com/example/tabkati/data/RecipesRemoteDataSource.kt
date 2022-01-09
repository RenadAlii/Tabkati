package com.example.tabkati.data


import com.example.tabkati.network.RecipeApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class RecipesRemoteDataSource @Inject constructor(private val api: RecipeApiService, private val dispatcher: CoroutineDispatcher=Dispatchers.IO
) {


    suspend fun getRandomRecipes(pages: String): SpoonacularRemoteDatasource =
        withContext(dispatcher){
            api.getRandomRecipes()
        }

    suspend fun getRecipesByCategory(category: String):List<RecipesItem?>? =
        withContext(dispatcher){
            api.getRecipesByCategory(category=category).recipes
        }

    suspend fun getRecipeById(id: String ):RecipesItem? =
        withContext(dispatcher){
            api.getRecipe(id)
        }
    suspend fun searchForRecipe(search: String ):List<ResultsItem?>? =
        withContext(dispatcher){
            api.searchForRecipes(search.trim()).results
        }

}