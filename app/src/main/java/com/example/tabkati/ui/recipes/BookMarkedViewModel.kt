package com.example.tabkati.ui.recipes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tabkati.domain.use_case.AddFavoriteRecipeUseCase
import com.example.tabkati.domain.use_case.BookmarkedUseCase
import com.example.tabkati.model.ExtendedIngredientsItem
import com.example.tabkati.model.RecipesModel
import com.example.tabkati.model.StepsItems
import com.example.tabkati.utils.RecipesApiStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BookMarkedViewModel @Inject constructor(private val bookmarkedUseCase: BookmarkedUseCase) :
    ViewModel() {


    // The internal MutableLiveData that stores the status of the most recent request.
    private val _status = MutableLiveData<RecipesApiStatus>()
    // The external immutable LiveData for the request status.
    val status: LiveData<RecipesApiStatus> = _status


    private var _bookMarkedUIState = MutableStateFlow(BookMarkedRecipesScreenUiState())
    val bookMarkedUIState = _bookMarkedUIState.asStateFlow()

    private val _recipesList = MutableLiveData<List<RecipesItemUiState>?>()
    val recipesList: LiveData<List<RecipesItemUiState>?> get() = _recipesList

    private var _taskList: MutableList<RecipesModel> = mutableListOf()
    val taskList: List<RecipesModel> get() = _taskList





    @InternalCoroutinesApi
    fun getBookMarkedRecipe() {
        viewModelScope.launch {
            try {
                _status.value = RecipesApiStatus.LOADING
                bookmarkedUseCase.getBookMark.invoke().collect {
                     _recipesList.value  = it.map {
                         RecipesItemUiState(
                             title = it?.title,
                             image = it?.image,
                             id = it?.id,
                             mintus =  it?.readyInMinutes.toString(),
                             serving = it?.servings.toString()

                         )
                     }
                         _bookMarkedUIState.update {
                             it.copy(recipesItems =   _recipesList.value)
                         }

                    _status.value = RecipesApiStatus.DONE

                }
            } catch (e: Exception) {
                _status.value = RecipesApiStatus.ERROR
            }
        }
    }

    @InternalCoroutinesApi
    fun deleteBookMarkedRecipe(bookmarked: RecipesDetailsScreenUiState) {
        val ingredientsList = bookmarked.extendedIngredients?.map {
            ExtendedIngredientsItem(amount = it?.amount, nameClean = it?.nameClean, unit = it?.unit)
        }
        val stepsList = bookmarked.StepsItemsUiState?.map {
            StepsItems(number = it?.number, step = it?.step, ingredients = null)
        }
        val recipe = RecipesModel(
            id = bookmarked.id,
            title = bookmarked.title,
            image = bookmarked.image,
            readyInMinutes = bookmarked.readyInMinutes,
            servings = bookmarked.servings,
            instructions = bookmarked.instruction,
            aggregateLikes = bookmarked.aggregateLikes,
            ingredients = ingredientsList,
            steps = stepsList
        )
        viewModelScope.launch {
            try {
                _status.value = RecipesApiStatus.LOADING
                bookmarkedUseCase.deleteBookMarked(recipe)
                getBookMarkedRecipe()
                _status.value = RecipesApiStatus.DONE

            } catch (e: Exception) {
                _status.value = RecipesApiStatus.ERROR

            }
        }
    }

    @InternalCoroutinesApi
    fun addBookMarkedRecipe(bookmarked: RecipesDetailsScreenUiState) {
        val ingredientsList = bookmarked.extendedIngredients?.map {
            ExtendedIngredientsItem(amount = it?.amount, nameClean = it?.nameClean, unit = it?.unit)
        }
        val stepsList = bookmarked.StepsItemsUiState?.map {
            StepsItems(number = it?.number, step = it?.step, ingredients = null)
        }
        val recipe = RecipesModel(
            id = bookmarked.id,
            title = bookmarked.title,
            image = bookmarked.image,
            readyInMinutes = bookmarked.readyInMinutes,
            servings = bookmarked.servings,
            instructions = bookmarked.instruction,
            aggregateLikes = bookmarked.aggregateLikes,
            ingredients = ingredientsList,
            steps = stepsList
        )


        viewModelScope.launch {
            try {
                _status.value = RecipesApiStatus.LOADING

                bookmarkedUseCase.addBookMarked(recipe)
                getBookMarkedRecipe()
                _status.value = RecipesApiStatus.DONE
            } catch (e: Exception) {
                _status.value = RecipesApiStatus.ERROR

            }

    }


}}