package com.example.tabkati

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tabkati.di.Servicelocator
import com.example.tabkati.network.RecipeApiService
import com.example.tabkati.ui.recipes.RecipesViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

//class RecipesViewModelFactory @Inject constructor (private val api: RecipeApiService,
//                                                   private val  dispatcher: CoroutineDispatcher): ViewModelProvider.Factory {
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//              if (modelClass.isAssignableFrom(RecipesViewModel::class.java)) {
//                @Suppress("UNCHECKED_CAST")
//                return RecipesViewModel(Servicelocator.provideRecipesRepository(api, dispatcher )) as T
//            }
//            else   throw IllegalArgumentException("Unknown ViewModel class")
//
//        }
//    }
//
