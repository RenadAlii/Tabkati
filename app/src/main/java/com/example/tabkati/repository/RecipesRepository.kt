package com.example.tabkati.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
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


    // fun to get all Recipes in the ROOM by calling getRandomRecipes() fun in DAO.
    suspend fun getAllRecipesE(): List<RecipesEntity> =
        withContext(Dispatchers.IO) { recipesDao.getRandomRecipes()}



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


    // fun to get random recipes from spoonacular API.
    suspend fun getRandomRecipes(pages: String = "10"): List<RecipesItemResponse?>? =
        recipesRemoteDataSource.getRandomRecipes(pages).recipes

    // fun to get recipes by category from spoonacular API.
    suspend fun getRecipesByCategory(category: String): List<RecipesItemResponse?>? =
        recipesRemoteDataSource.getRecipesByCategory(category)

    // fun to get the recipe details from spoonacular API.
    suspend fun getRecipeById(id: String): RecipesItemResponse? = recipesRemoteDataSource.getRecipeById(id)
    // fun to return list of recipes using the search query from spoonacular API.
    suspend fun searchForRecipes(search: String): List<ResultsItem?>? =
        recipesRemoteDataSource.searchForRecipe(search)

}