package com.example.tabkati.domain.repository

import com.example.tabkati.model.RecipesModel
import kotlinx.coroutines.flow.Flow

interface Favorite {

    fun getFavoriteRecipesFromFirestore(): Flow<List<RecipesModel>>

    suspend fun addFavoriteRecipesToFirestore( listOfBookmarked:  List<RecipesModel>)

    suspend fun deleteFavoriteRecipesFromFirestore(listWithoutDeletedRecipe: List<RecipesModel?>?)


}