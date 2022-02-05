package com.example.tabkati.ui.recipes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tabkati.repository.RecipesRepository
import com.example.tabkati.utils.RecipesApiStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RecipesByCatViewModel @Inject constructor(private val repository: RecipesRepository) :
    ViewModel() {


    // The internal MutableLiveData that stores the status of the most recent request.
    private val _status = MutableLiveData<RecipesApiStatus>()

    // The external immutable LiveData for the request status.
    val status: LiveData<RecipesApiStatus> = _status



    private val _recipesList = MutableLiveData<List<RecipesItemUiState?>?>()
    val recipesList: LiveData<List<RecipesItemUiState?>?> get() = _recipesList

    private var _categoryId = MutableLiveData<String>()
    val categoryId: LiveData<String?> get() = _categoryId

    private var _respicesUIState = MutableStateFlow(RecipesByCatScreenUiState())
    val respicesUIState = _respicesUIState.asStateFlow()


    // fun to set the category query.
    fun setCategoryId(query: String) {
        _categoryId.value = query
        getRecipeByCategory()
    }



    // fun to get the recipe details.
    private fun getRecipeByCategory() {
        viewModelScope.launch {

            try {
                _respicesUIState.update {
                    it.copy(status = RecipesApiStatus.LOADING)
                }
                val result = repository.getRecipesByCategory(_categoryId.value!!)
                _recipesList.value = result?.map {
                    RecipesItemUiState(
                        title = it?.title,
                        image = it?.image,
                        id = it?.id,
                        mintus = it?.readyInMinutes?.toString(),
                        serving = it?.servings.toString())
                }

                _respicesUIState.update { it.copy(recipesItems = _recipesList.value, status = RecipesApiStatus.DONE) }
            } catch (e: Exception) {
                _respicesUIState.update {
                    it.copy(status = RecipesApiStatus.ERROR)
                }
            }

        }
    }


}