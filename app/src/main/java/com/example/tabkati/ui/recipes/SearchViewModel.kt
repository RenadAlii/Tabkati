package com.example.tabkati.ui.recipes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tabkati.data.ResultsItem
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

    private val _recipesList = MutableLiveData <List<ResultsItem?>?>()
    val recipesList: LiveData<List<ResultsItem?>?> = _recipesList

    private val _search = MutableLiveData <String>()
    val search: LiveData<String> = _search



    // fun to set the search.
    fun setSearchQuery(query: String){
        _search.value = query
        getRecipes()
    }


    // fun to set the search.
    fun setSearchState(state: RecipesApiStatus){
        _status.value = state
    }

    // fun to get recipes.
    fun getRecipes() {
        viewModelScope.launch {
            try {
                _status.value = RecipesApiStatus.LOADING
             _recipesList.value = repository.searchForRecipes(_search.value!!)
                _status.value = RecipesApiStatus.DONE
                if( _recipesList.value.isNullOrEmpty()){
                    _status.value = RecipesApiStatus.ERROR
                }
            } catch (e: Exception) {
                _status.value = RecipesApiStatus.ERROR
                // to clear the RecyclerView.

            }
        }
    }







}