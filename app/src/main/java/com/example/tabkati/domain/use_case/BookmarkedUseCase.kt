package com.example.tabkati.domain.use_case

import com.example.tabkati.utils.BookMark

data class BookmarkedUseCase(
    val addBookMarked: AddFavoriteRecipeUseCase,
    val deleteBookMarked: DeleteFavoriteRecipesUseCase,
    val getBookMark: GetFavoriteRecipesUseCase)
