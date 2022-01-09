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
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(private val repository: RecipesRepository) : ViewModel() {


    // The internal MutableLiveData that stores the status of the most recent request.
    private val _status = MutableLiveData<RecipesApiStatus>()
    // The external immutable LiveData for the request status.
    val status: LiveData<RecipesApiStatus> get() =  _status

    private var _recipeId = MutableLiveData<String?>()
    val recipeId: LiveData<String?> get() =  _recipeId

    private var _recipe = MutableLiveData<RecipesItemResponse?>()
    val recipe: LiveData<RecipesItemResponse?> get() =  _recipe



//    private val _uiState: MutableStateFlow<StepsUiState>  = MutableStateFlow(StepsUiState())
//    val uiState: StateFlow<StepsUiState> = _uiState.asStateFlow()


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
    }

    private var fetchJob: Job? = null

//    fun fetchRecipeDetails(id: String) {
//        fetchJob?.cancel()
//        fetchJob = viewModelScope.launch {
//            try {
//                val recipeItem = repository.getRecipeById(id)
//                val stepsItem = recipeItem?.analyzedInstructions
//                _uiState.update {
////                    it.copy(
////                        steps = recipeItem.analyzedInstructions
////
////                    )
//               // }
//            } catch (ioe: IOException) {
//                // Handle the error and notify the notify the UI when appropriate.
//                _uiState.update {
//                    val messages = getMessagesFromThrowable(ioe)
//                    it.copy(userMessages = messages)
//                }
//            }
     //  }
    //}



}

