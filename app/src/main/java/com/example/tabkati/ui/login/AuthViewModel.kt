package com.example.tabkati.ui.login


import androidx.lifecycle.*
import com.example.tabkati.di.FirebaseModule
import com.example.tabkati.repository.AuthRepository
import com.example.tabkati.ui.recipes.AuthScreenUiState
import com.example.tabkati.utils.RecipesApiStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


// here we emit the data that is collected from the repository class.
@InternalCoroutinesApi
@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository,
) : ViewModel() {


    private var _errorEnableMsg = MutableLiveData("")
    val errorEnableMsg: LiveData<String> get() = _errorEnableMsg

    private var _authUIState = MutableStateFlow(AuthScreenUiState())
    val authUIState = _authUIState.asStateFlow()



    fun signInWithGoogle(idToken: String) = liveData(Dispatchers.IO) {
        repository.signInWithGoogle(idToken).collect { response ->
            emit(response)
        }
    }


    fun createUser() = liveData(Dispatchers.IO) {
        repository.createUser().collect { response ->
            emit(response)
        }
    }

    suspend fun createUser(name: String): Flow<Boolean> =
        repository.createUser(name)


    // fun to check if the user email is empty if true errorEnableMsg = email.
    private fun isEmailEmpty(email: String): Boolean {
        return if (email.isEmpty()) {
            _errorEnableMsg.value += "email"
            false
        } else
            true
    }

    private fun isUserNameEmpty(username: String): Boolean {
        return if (username.isEmpty()) {
            _errorEnableMsg.value += "name"
            false
        } else true

    }

    private fun isUserPasswordEmpty(userPassword: String): Boolean {
        return if (userPassword.isEmpty()) {
            _errorEnableMsg.value += "password"
            false
        } else true
    }

    fun isUserInfoForSignUpNotEmpty(
        userPassword: String,
        userName: String,
        email: String,
    ): Boolean {
        // clear ErrorEnableMsg.
        clearErrorEnableMsg()
        if (isUserPasswordEmpty(userPassword)) {
            if (isUserNameEmpty(userName)) {
                if (isEmailEmpty(email)) {
                    // if all user info are not empty  signUp the User
                    signUpUser(userPassword, email, userName)
                    return true
                }
            }
        } else if (isUserNameEmpty(userName)) {
            if (isEmailEmpty(email)) {
                return false
            }

        } else if (isEmailEmpty(email)) {
            return false
        }
        return false
    }

    private fun clearErrorEnableMsg() {
        _errorEnableMsg.value = ""
    }


    // fun to check if the user Info is empty or Not for Login.
    fun isUserInfoForLogInNotEmpty(userPassword: String, email: String): Boolean {
        // clear ErrorEnableMsg.
        clearErrorEnableMsg()
        if (isUserPasswordEmpty(userPassword)) {
            if (isEmailEmpty(email)) {
                // if all user info are not empty  signUp the User
                loginUpUser(userPassword, email)
                return true
            }
        } else
            if (isEmailEmpty(email)) {
                return false
            }
        return false
    }

    // fun to Login using firebase by user email & password.
    private fun loginUpUser(userPassword: String, email: String) {
        _authUIState.update {
            it.copy(status = RecipesApiStatus.LOADING)
        }
        FirebaseModule.provideFirebaseAuthInstance()
            .signInWithEmailAndPassword(email.trim(), userPassword.trim())
            .addOnCanceledListener {
                _authUIState.update {
                    it.copy(status = RecipesApiStatus.ERROR,
                        msg = "canceled")
                }
            }
            .addOnFailureListener { e ->
                //setToastMsg(it.message.toString())
                _authUIState.update {
                    it.copy(status = RecipesApiStatus.ERROR,
                        msg = e.message.toString())
                }
            }
            .addOnSuccessListener { user ->
                _authUIState.update {
                    it.copy(status = RecipesApiStatus.DONE,
                        msg = "success ${user.user?.email}")
                }


            }
    }

    // fun to sign up using firebase by user name, password and email.
    private fun signUpUser(userPassword: String, email: String, name: String) {
        _authUIState.update {
            it.copy(status = RecipesApiStatus.LOADING)
        }
        FirebaseModule.provideFirebaseAuthInstance()
            .createUserWithEmailAndPassword(email.trim(), userPassword.trim())
            .addOnCanceledListener {
                _authUIState.update {
                    it.copy(status = RecipesApiStatus.ERROR,
                        msg = "canceled")
                }
            }
            .addOnFailureListener { e ->
                _authUIState.update {
                    it.copy(status = RecipesApiStatus.ERROR,
                        msg = e.message.toString())
                }
            }
            .addOnSuccessListener { user ->

                viewModelScope.launch {
                    createUser(name).collect {
                        if (it) {
                            _authUIState.update { uistate ->
                                uistate.copy(status = RecipesApiStatus.DONE,
                                    msg = "success ${user.user?.email}")
                            }
                        }
                    }
                }
            }
    }
}









