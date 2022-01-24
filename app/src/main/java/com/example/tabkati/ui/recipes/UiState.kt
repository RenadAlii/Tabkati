package com.example.tabkati.ui.recipes

import com.example.tabkati.R
import com.example.tabkati.utils.RecipesApiStatus


data class RecipesItemUiState(
    val id: Int? = 0,
    val image: String? = "",
    val title: String? = "",
    val mintus: String? = "",
    val serving: String?,
)

data class CategoryUIState(
    val id: String = "",
    val title: String = "",
    val image: Int = R.drawable.bancake,
)


data class RecipesScreenUiState(
    val category: List<CategoryUIState> = listOf(),
    val recipesItems: List<RecipesItemUiState?>? = listOf(),
    val userName: String? = "",
    val status: RecipesApiStatus=RecipesApiStatus.LOADING
)

data class RecipesByCatScreenUiState(
    val recipesItems: List<RecipesItemUiState?>? = listOf(),
    val status: RecipesApiStatus=RecipesApiStatus.LOADING
)

data class BookMarkedRecipesScreenUiState(
    val recipesItems: List<RecipesItemUiState>? = listOf()
)



data class UserProfileScreenUiState(
    val name: String? = "",
    val email: String? = "",
    val loading: Boolean = false,
    val errorMsg: String = "",
)


data class ExtendedIngredientsItemUiState(
    val nameClean: String? = "",
    val amount: Double? = 0.0,
    val unit: String? = "",
)


data class RecipesDetailsScreenUiState(
    val id: Int? =0,
    val image: String? = "",
    val title: String? = "",
    val readyInMinutes: Int? = 0,
    val servings: Int? = 0,
    val instruction: String? = "",
    val aggregateLikes: Int? = 0,
    val extendedIngredients: List<ExtendedIngredientsItemUiState?>? = listOf(),
    val StepsItemsUiState: List<StepsItemsUiState?>? = listOf(),

)



data class StepsItemsUiState(
    val number: Int? = 0,
    val step: String? = "",
    val ingredients: List<IngredientsUiState?>? = listOf(),
)

data class IngredientsUiState(
                              val name: String? = "",) {

}
