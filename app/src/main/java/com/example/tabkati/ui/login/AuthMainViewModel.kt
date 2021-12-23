package com.example.tabkati.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.tabkati.repository.MainAuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import javax.inject.Inject


@HiltViewModel
class AuthMainViewModel @Inject constructor(
    private val repository: MainAuthRepository
): ViewModel() {

    fun signOut() = liveData(Dispatchers.IO) {
        repository.signOut().collect { response ->
            emit(response)
        }
    }

    @ExperimentalCoroutinesApi
    fun getAuthState() = liveData(Dispatchers.IO) {
        repository.getFirebaseAuthState().collect { response ->
            emit(response)
        }
    }
}