package com.example.tabkati.domain.useCase

data class BookmarkedUseCase(
    val addBookMarked: AddFavoriteRecipeUseCase,
    val deleteBookMarked: DeleteFavoriteRecipesUseCase,
    val getBookMark: GetFavoriteRecipesUseCase)
