package com.example.tabkati.ui.recipes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tabkati.data.RecipeCategoriesPictureDataSource
import com.example.tabkati.repository.RecipeCategoriesRepository
import com.example.tabkati.repository.RecipesRepository
import com.example.tabkati.utils.RecipesApiStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomRecipesViewModel @Inject constructor(private val recipesRepository: RecipesRepository,
private val categoryRepository: RecipeCategoriesRepository
) :
    ViewModel() {


    // The internal MutableLiveData that stores the status of the most recent request.
    private val _status = MutableLiveData<RecipesApiStatus>()

    // The external immutable LiveData for the request status.
    val status: LiveData<RecipesApiStatus> = _status


    private val _recipesList = MutableLiveData<List<RecipesItemUiState?>?>()
    val recipesList: LiveData<List<RecipesItemUiState?>?> get() = _recipesList


    private var _respicesUIState = MutableStateFlow(RecipesScreenUiState())
    val respicesUIState = _respicesUIState.asStateFlow()



    init {
        viewModelScope.launch {
            recipesRepository.refreshRecipes()
        }
        getCategories()
    }



    // fun to get the Random Recipes from Room.
    fun getRandomRecipes() {
        viewModelScope.launch {
            try {
                _respicesUIState.update {
                    it.copy(status = RecipesApiStatus.LOADING)
                }
                val result = recipesRepository.getAllRecipesE()
                _recipesList.value = result.map {
                    RecipesItemUiState(
                        title = it.title,
                        image = it.image,
                        id = it.id,
                        mintus = it.readyInMinutes.toString(),
                        serving = it.servings.toString()


                    )
                }
                _respicesUIState.update {
                    it.copy(recipesItems = _recipesList.value, status = RecipesApiStatus.DONE)
                }
            } catch (e: Exception) {
                _respicesUIState.update {
                    it.copy(status = RecipesApiStatus.ERROR)
                }
            }

        }
    }


    private fun getCategories() {
        val recipeCategoriesData =
            categoryRepository.getCategoriesList().map {
                CategoryUIState(it.id,
                    it.titleOFCat,
                    it.CategoryImage)
            }

        _respicesUIState.update { it.copy(category = recipeCategoriesData) }
    }

}