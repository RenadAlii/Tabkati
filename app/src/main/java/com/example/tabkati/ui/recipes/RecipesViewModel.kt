package com.example.tabkati.ui.recipes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tabkati.data.RecipesItem
import com.example.tabkati.repository.RecipesRepository
import com.example.tabkati.utils.Constants.TAG
import com.example.tabkati.utils.RecipesApiStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RecipesViewModel @Inject constructor(private val repository: RecipesRepository) :
    ViewModel() {


    // The internal MutableLiveData that stores the status of the most recent request.
    private val _status = MutableLiveData<RecipesApiStatus>()

    // The external immutable LiveData for the request status.
    val status: LiveData<RecipesApiStatus> = _status

    private val _recipesList = MutableLiveData<List<RecipesItem?>?>()
    val recipesList: LiveData<List<RecipesItem?>?> = _recipesList


    private var _categoryId = MutableLiveData<String>()
    val categoryId: LiveData<String?> get() = _categoryId


    // fun to set the category query.
    fun setCategoryId(query: String) {
        _categoryId.value = query
        getRecipeByCategory()
    }

    init {
        getRandomRecipes()

    }


    // fun to get the recipe details.
    private fun getRecipeByCategory() {
        viewModelScope.launch {
            _status.value = RecipesApiStatus.LOADING

            try {
                _recipesList.value = repository.getRecipesByCategory(_categoryId.value!!)
                _status.value = RecipesApiStatus.DONE
            } catch (e: Exception) {
                _status.value = RecipesApiStatus.ERROR
                Log.e(TAG, "getRecipeByCategory:${e.message} ")
            }
        }
    }


    // fun to get the popular movie.
    fun getRandomRecipes() {
        viewModelScope.launch {
            _status.value = RecipesApiStatus.LOADING
            try {
                _recipesList.value = repository.getRandomRecipes()
                _status.value = RecipesApiStatus.DONE
            } catch (e: Exception) {
                _status.value = RecipesApiStatus.ERROR
                // to clear the RecyclerView.

            }
        }
    }


}