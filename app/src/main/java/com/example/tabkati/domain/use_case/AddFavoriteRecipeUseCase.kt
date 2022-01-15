package com.example.tabkati.domain.use_case

import com.example.tabkati.domain.repository.Favorite
import com.example.tabkati.model.RecipesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext

class AddFavoriteRecipeUseCase(
    private val repository: Favorite,
) {
    suspend operator fun invoke(listOFFavoriteRecipe: RecipesModel) = withContext(Dispatchers.IO) {
        repository.getFavoriteRecipesFromFirestore().collect {
            val list = it.toMutableList()
            list.add(listOFFavoriteRecipe)
            repository.addFavoriteRecipesToFirestore(list)
        }
    }
}