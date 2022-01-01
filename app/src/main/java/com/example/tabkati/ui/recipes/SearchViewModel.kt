package com.example.tabkati.ui.recipes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tabkati.data.RecipesItem
import com.example.tabkati.repository.MainAuthRepository
import com.example.tabkati.repository.RecipesRepository
import com.example.tabkati.utils.RecipesApiStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: RecipesRepository) : ViewModel() {


    // The internal MutableLiveData that stores the status of the most recent request.
    private val _status = MutableLiveData<RecipesApiStatus>()
    // The external immutable LiveData for the request status.
    val status: LiveData<RecipesApiStatus> = _status

    private val _recipesList = MutableLiveData <List<RecipesItem?>?>()
    val recipesList: LiveData<List<RecipesItem?>?> = _recipesList

    private val _search = MutableLiveData <List<RecipesItem?>?>()
    val search: LiveData<List<RecipesItem?>?> = _search



    // fun to get recipes.
    fun getRecipes() {
        viewModelScope.launch {
            _status.value = RecipesApiStatus.LOADING
            try {
               // _recipesList.value =repository.searchForRecipes()
                _status.value = RecipesApiStatus.DONE
            } catch (e: Exception) {
                _status.value = RecipesApiStatus.ERROR
                // to clear the RecyclerView.

            }
        }
    }







}