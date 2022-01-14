package com.example.tabkati.domain.use_case

import com.example.tabkati.domain.repository.Favorite

class GetFavoriteRecipesUseCase(
    private val repository: Favorite,
) {
    operator fun invoke() = repository.getFavoriteRecipesFromFirestore()
}