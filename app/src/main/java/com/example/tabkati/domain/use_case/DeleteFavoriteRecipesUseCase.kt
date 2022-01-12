package com.example.tabkati.domain.use_case

import com.example.tabkati.domain.repository.Favorite

class DeleteFavoriteRecipesUseCase(private val repository: Favorite
) {
    suspend operator fun invoke(id: String) = repository.deleteFavoriteRecipesFromFirestore(id)
}