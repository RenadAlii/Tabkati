package com.example.tabkati.ui.recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.tabkati.repository.MainAuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MainAuthRepository
): ViewModel() {

    // fun to sigOut from firebase auth.
    fun sigOut() = liveData(Dispatchers.IO) {
        repository.signOut().collect{
            emit(it)
        }
    }

    // fun to get the state of the user auth is the user authenticated or not.
    fun getAuthState() = liveData(Dispatchers.IO) {
        repository.getFirebaseAuthState().collect{
            emit(it)
        }
    }
}