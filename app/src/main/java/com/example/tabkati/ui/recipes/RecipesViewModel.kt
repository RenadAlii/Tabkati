package com.example.tabkati.ui.recipes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tabkati.data.RecipesItem
import com.example.tabkati.repository.RecipesRepository
import com.example.tabkati.utils.RecipesApiStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RecipesViewModel @Inject constructor(private val repository: RecipesRepository) : ViewModel() {


    // The internal MutableLiveData that stores the status of the most recent request.
    private val _status = MutableLiveData<RecipesApiStatus>()
    // The external immutable LiveData for the request status.
    val status: LiveData<RecipesApiStatus> = _status

    private val _RecipesList = MutableLiveData <List<RecipesItem?>?>()
    val recipesList: LiveData<List<RecipesItem?>?> = _RecipesList

init {
    getRandomRecipes()
}


    // fun to get the popular movie.
    fun getRandomRecipes() {
        viewModelScope.launch {
            _status.value = RecipesApiStatus.LOADING
            try {
                _RecipesList.value =repository.getRandomRecipes()
                _status.value = RecipesApiStatus.DONE
            } catch (e: Exception) {
                _status.value = RecipesApiStatus.ERROR
                // to clear the RecyclerView.

            }
        }
    }




}