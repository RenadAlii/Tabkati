package com.example.tabkati.domain.use_case

data class BookmarkedUseCase(
    val addBookMarked: AddFavoriteRecipeUseCase,
    val deleteBookMarked: DeleteFavoriteRecipesUseCase,
    val getBookMark: GetFavoriteRecipesUseCase)
