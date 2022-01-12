package com.example.tabkati.domain.use_case

import com.example.tabkati.domain.repository.Favorite
import com.example.tabkati.model.ExtendedIngredientsItem
import com.example.tabkati.model.StepsItems

class AddFavoriteRecipeUseCase (
    private val repository: Favorite) {
    suspend operator fun invoke(
        id: Int?,
        title: String?,
        image: String?,
        servings: Int?,
        aggregateLikes: Int?,
        readyInMinutes: Int?,
        sourceUrl: String? = null,
        ingredients: List<ExtendedIngredientsItem?>?,
        steps: List<StepsItems?>?,
        instructions: String?
    ) = repository.addFavoriteRecipesToFirestore(id,title, image, servings,
        aggregateLikes, readyInMinutes, sourceUrl, ingredients, steps, instructions)
}