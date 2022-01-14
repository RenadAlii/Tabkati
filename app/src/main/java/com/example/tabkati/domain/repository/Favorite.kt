package com.example.tabkati.domain.repository

import com.example.tabkati.model.ExtendedIngredientsItem
import com.example.tabkati.model.RecipesModel
import com.example.tabkati.model.StepsItems
import com.example.tabkati.utils.State
import kotlinx.coroutines.flow.Flow

interface Favorite {

    fun getFavoriteRecipesFromFirestore(): Flow<List<RecipesModel>>

    suspend fun addFavoriteRecipesToFirestore( listOfBookmarked:  List<RecipesModel>)

    suspend fun deleteFavoriteRecipesFromFirestore(listWithoutDeletedRecipe: List<RecipesModel?>?)


}