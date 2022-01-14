package com.example.tabkati.domain.use_case

import com.example.tabkati.domain.repository.Favorite
import com.example.tabkati.model.RecipesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext

class DeleteFavoriteRecipesUseCase( private val repository: Favorite) {
    @InternalCoroutinesApi
    suspend operator fun invoke(deletedRecipe: RecipesModel)
            = withContext(Dispatchers.IO) {
        repository.getFavoriteRecipesFromFirestore().collect {
            val list= it.toMutableList()
            list.remove(deletedRecipe)
            repository.deleteFavoriteRecipesFromFirestore(list)
        }


    }}