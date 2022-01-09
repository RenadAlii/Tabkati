package com.example.tabkati.repository

import com.example.tabkati.data.RecipesItemResponse
import com.example.tabkati.data.RecipesRemoteDataSource
import com.example.tabkati.data.ResultsItem
import com.example.tabkati.data.asDatabaseModel
import com.example.tabkati.data.database.RecipesDao
import com.example.tabkati.data.database.RecipesEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecipesRepository @Inject constructor(
    private val recipesRemoteDataSource: RecipesRemoteDataSource,
    private val recipesDao: RecipesDao,
) {

    suspend fun getAllRecipesE(): List<RecipesEntity> =
        withContext(Dispatchers.IO) { recipesDao.getRandomRecipes() }


    // list of recipes that can be show on the screen.


    /**
     * Refresh the Recipes stored in the offline cache.
     *
     * This function uses the IO dispatcher to ensure the database insert database operation
     * happens on the IO dispatcher. By switching to the IO dispatcher using `withContext` this
     * function is now safe to call from any thread including the Main thread.
     *
     */

    suspend fun refreshRecipes() {
        withContext(Dispatchers.IO) {
            val randomRecipes = recipesRemoteDataSource.getRandomRecipes("10")
            randomRecipes.asDatabaseModel()?.let { recipesDao.insertRecipes(*it) }


        }


    }


    suspend fun getRandomRecipes(pages: String = "10"): List<RecipesItemResponse?>? =
        recipesRemoteDataSource.getRandomRecipes(pages).recipes

    suspend fun getRecipesByCategory(category: String): List<RecipesItemResponse?>? =
        recipesRemoteDataSource.getRecipesByCategory(category)

    suspend fun getRecipeById(id: String): RecipesItemResponse? = recipesRemoteDataSource.getRecipeById(id)
    suspend fun searchForRecipes(search: String): List<ResultsItem?>? =
        recipesRemoteDataSource.searchForRecipe(search)

}