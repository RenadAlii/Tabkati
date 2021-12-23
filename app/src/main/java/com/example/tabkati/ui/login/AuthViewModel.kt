package com.example.tabkati.ui.login


import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.tabkati.repository.AuthRepository
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import javax.inject.Inject


// here we emit the data that is collected from the repository class.
@HiltViewModel
class AuthViewModel @Inject constructor(
   private val repository: AuthRepository
): ViewModel() {

    fun signInWithGoogle(idToken: String) = liveData(Dispatchers.IO) {
        repository.firebaseSignInWithGoogle(idToken).collect { response ->
            emit(response)
        }
    }

    fun createUser() = liveData(Dispatchers.IO) {
        repository.createUserInFireStore().collect { response ->
            emit(response)
        }
    }
}