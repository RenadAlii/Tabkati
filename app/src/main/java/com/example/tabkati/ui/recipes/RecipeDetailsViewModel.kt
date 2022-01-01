package com.example.tabkati.ui.recipes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tabkati.data.RecipesItem
import com.example.tabkati.repository.RecipesRepository
import com.example.tabkati.utils.Constants
import com.example.tabkati.utils.RecipesApiStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(private val repository: RecipesRepository) : ViewModel() {


    // The internal MutableLiveData that stores the status of the most recent request.
    private val _status = MutableLiveData<RecipesApiStatus>()
    // The external immutable LiveData for the request status.
    val status: LiveData<RecipesApiStatus> = _status

    private var _recipeId = MutableLiveData<String?>()
    val recipeId: LiveData<String?> = _recipeId

    private var _recipe = MutableLiveData<RecipesItem?>()
    val recipe: LiveData<RecipesItem?> = _recipe




    // fun to set the recipe id.
    fun setRecipeId(id: String){
        _recipeId.value = id
        getRecipeDetails()
    }

    private fun getRecipeDetails() {
        viewModelScope.launch {
            _status.value = RecipesApiStatus.LOADING

            try {
                _recipe.value = repository.getRecipeById(_recipeId.value!!)
                Log.e(Constants.TAG, "......: ${_recipe.value}", )
                _status.value = RecipesApiStatus.DONE
            } catch (e: Exception) {
                _status.value = RecipesApiStatus.ERROR
                Log.e(Constants.TAG, "getRecipeById:${e.message}${_recipeId.value!!} ", )
            }
        }
    }}

