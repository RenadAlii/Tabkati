package com.example.tabkati.domain.repository

import com.example.tabkati.model.RecipesModel
import kotlinx.coroutines.flow.Flow

interface Favorite {

    fun getFavoriteRecipesFromFirestore(): Flow<List<RecipesModel>>

    suspend fun addFavoriteRecipesToFirestore(title: String, author: String): Flow<Void>

    suspend fun deleteFavoriteRecipesFromFirestore(bookId: String): Flow<Void>


}