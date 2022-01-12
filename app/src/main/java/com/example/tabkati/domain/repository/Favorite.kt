package com.example.tabkati.domain.repository

import com.example.tabkati.model.ExtendedIngredientsItem
import com.example.tabkati.model.RecipesModel
import com.example.tabkati.model.StepsItems
import kotlinx.coroutines.flow.Flow

interface Favorite {

    fun getFavoriteRecipesFromFirestore(): Flow<List<RecipesModel>>

    suspend fun addFavoriteRecipesToFirestore( id: Int?,
                                               title: String?,
                                               image: String?,
                                               servings: Int?,
                                               aggregateLikes: Int?,
                                               readyInMinutes: Int?,
                                               sourceUrl: String? = null,
                                               ingredients: List<ExtendedIngredientsItem?>?,
                                               steps: List<StepsItems?>?,
                                               instructions: String?): Flow<Void>

    suspend fun deleteFavoriteRecipesFromFirestore(Id: String): Flow<Void>


}