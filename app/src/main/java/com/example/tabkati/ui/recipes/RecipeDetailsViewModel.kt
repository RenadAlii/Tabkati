package com.example.tabkati.ui.recipes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tabkati.data.RecipesItemResponse
import com.example.tabkati.repository.RecipesRepository
import com.example.tabkati.utils.Constants
import com.example.tabkati.utils.RecipesApiStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(private val repository: RecipesRepository) : ViewModel() {


    // The internal MutableLiveData that stores the status of the most recent request.
    private val _status = MutableLiveData<RecipesApiStatus>()
    // The external immutable LiveData for the request status.
    val status: LiveData<RecipesApiStatus> get() =  _status

    private var _recipeId = MutableLiveData<String>()
    val recipeId: LiveData<String> get() =  _recipeId

    private var _recipe = MutableLiveData<RecipesItemResponse?>()
    val recipe: LiveData<RecipesItemResponse?> get() =  _recipe

    private val _ingredientList = MutableLiveData<List<ExtendedIngredientsItemUiState?>?>()
    val ingredientList: LiveData<List<ExtendedIngredientsItemUiState?>?> get() = _ingredientList

    private val _stepList = MutableLiveData<List<StepsItemsUiState?>?>()
    val stepList: LiveData<List<StepsItemsUiState?>?> get() = _stepList


    private val _uiState: MutableStateFlow<RecipesDetailsScreenUiState>  = MutableStateFlow(RecipesDetailsScreenUiState())
    val uiState: StateFlow<RecipesDetailsScreenUiState> = _uiState.asStateFlow()


    // fun to set the recipe id.
    fun setRecipeId(id: String){
        _recipeId.value = id
        getRecipeDetails()
    }

    private fun getRecipeDetails() {
        viewModelScope.launch {
            _status.value = RecipesApiStatus.LOADING

            try {
                val recipesDetailsResult = repository.getRecipeById(_recipeId.value!!)
                _ingredientList.value =recipesDetailsResult?.extendedIngredients?.map {
                    ExtendedIngredientsItemUiState(
                        nameClean = it?.nameClean,
                        amount = it?.amount,
                        unit = it?.unit
                    )
                }
                _uiState.update { it.copy(
                    id = recipesDetailsResult?.id,
                        image = recipesDetailsResult?.image,
                        title = recipesDetailsResult?.title,
                        readyInMinutes = recipesDetailsResult?.readyInMinutes,
                        servings = recipesDetailsResult?.servings,
                    instruction = recipesDetailsResult?.instructions,
                        aggregateLikes = recipesDetailsResult?.aggregateLikes,
                       extendedIngredients = _ingredientList.value)}

                Log.e(Constants.TAG, "......: ${_recipe.value}" )
                _status.value = RecipesApiStatus.DONE
            } catch (e: Exception) {
                _status.value = RecipesApiStatus.ERROR
                Log.e(Constants.TAG, "getRecipeById:${e.message}${_recipeId.value!!} ", )
            }
        }
    }



}

