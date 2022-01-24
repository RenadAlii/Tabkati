package com.example.tabkati.domain.useCase

import com.example.tabkati.domain.repository.Favorite

class GetFavoriteRecipesUseCase(
    private val repository: Favorite,
) {
    operator fun invoke() = repository.getFavoriteRecipesFromFirestore()
}