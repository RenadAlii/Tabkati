package com.example.tabkati.ui.recipes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tabkati.data.RecipeCategoriesPictureDataSource
import com.example.tabkati.data.RecipesItemResponse
import com.example.tabkati.data.database.RecipesEntity
import com.example.tabkati.repository.RecipesRepository
import com.example.tabkati.utils.Constants.TAG
import com.example.tabkati.utils.RecipesApiStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import javax.inject.Inject


@HiltViewModel
class RecipesViewModel @Inject constructor(private val repository: RecipesRepository) :
    ViewModel() {


    // The internal MutableLiveData that stores the status of the most recent request.
    private val _status = MutableLiveData<RecipesApiStatus>()

    // The external immutable LiveData for the request status.
    val status: LiveData<RecipesApiStatus> = _status

    private val _recipesListE =
        MutableLiveData<List<RecipesEntity>>()
    val recipesListE: LiveData<List<RecipesEntity>> get() = _recipesListE

    private val _recipesList = MutableLiveData<List<RecipesItemUiState?>?>()
    val recipesList: LiveData<List<RecipesItemUiState?>?> get() = _recipesList

    private var _categoryId = MutableLiveData<String>()
    val categoryId: LiveData<String?> get() = _categoryId

    private var _respicesUIState = MutableStateFlow(RecipesScreenUiState())
    val respicesUIState = _respicesUIState.asStateFlow()


    // fun to set the category query.
    fun setCategoryId(query: String) {
        _categoryId.value = query
        getRecipeByCategory()
    }

    init {
        viewModelScope.launch {
           // repository.refreshRecipes()
        }

        _status.value = RecipesApiStatus.LOADING
        getRandomRecipes()
        getCatories()
        getRandomRecipesE()

        _status.value = RecipesApiStatus.DONE

    }
fun getCatories(){
     val recipeCategoriesData = RecipeCategoriesPictureDataSource.recipeCategoriesPictureList.map { CategoryUIState(it.id,it.titleOFCat,it.CategoryImage) }

    _respicesUIState.update { it.copy(category = recipeCategoriesData) }
}

    // fun to get the recipe details.
    private fun getRecipeByCategory() {
        viewModelScope.launch {

            try {
                val result = repository.getRecipesByCategory(_categoryId.value!!)
                _recipesList.value  = result?.map {
                    RecipesItemUiState(
                        title = it?.title!!,
                        image = it?.image!!,
                        id = it?.id!!,
                        mintus =  it?.readyInMinutes!!.toString(),
                        serving = it?.servings.toString() )
                }

                _respicesUIState.update { it.copy(recipesItems = _recipesList.value)}
            } catch (e: Exception) {
                Log.e(TAG, "getRecipeByCategory:${e.message} ")
                _status.value = RecipesApiStatus.ERROR

            }

        }
    }


    // fun to get the popular movie.
    fun getRandomRecipes() {
        viewModelScope.launch {
            try {

                val result = repository.getRandomRecipes()
                _recipesList.value = result?.map {
                    RecipesItemUiState(
                        title = it?.title,
                        image = it?.image,
                        id = it?.id,
                        mintus =  it?.readyInMinutes.toString(),
                        serving = it?.servings.toString()

                    )
                }
                _respicesUIState.update {
                    it.copy(recipesItems =  _recipesList.value )
                }
            } catch (e: Exception) {
                _status.value = RecipesApiStatus.ERROR
            }

        }
    }


    // fun to get the popular movie.
    fun getRandomRecipesE() {
        viewModelScope.launch {
            try {
                _recipesListE.value = repository.getAllRecipesE()
            } catch (e: Exception) {
                _status.value = RecipesApiStatus.ERROR
                // to clear the RecyclerView.

            }

        }
    }


}