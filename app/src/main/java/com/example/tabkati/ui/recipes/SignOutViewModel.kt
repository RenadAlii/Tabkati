package com.example.tabkati.ui.recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.tabkati.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class SignOutViewModel @Inject constructor(
    private val repository: AuthRepository
): ViewModel() {

    // fun to signOut from firebase auth.
    fun signOut() = liveData(Dispatchers.IO) {
        repository.signOut().collect{
            emit(it)
        }
    }

    // fun to get the state of the user auth is the user authenticated or not.
    fun getAuthState() = liveData(Dispatchers.IO) {
        repository.getAuthState().collect{
            emit(it)
        }
    }



}