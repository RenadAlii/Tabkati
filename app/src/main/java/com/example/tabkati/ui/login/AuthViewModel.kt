package com.example.tabkati.ui.login


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.tabkati.R
import com.example.tabkati.di.FirebaseModule
import com.example.tabkati.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import javax.inject.Inject


// here we emit the data that is collected from the repository class.
@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {


    private var _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> get() = _toastMessage

    private var _errorEnableMsg = MutableLiveData("")
    val errorEnableMsg: LiveData<String> get() = _errorEnableMsg

    private var _userEmail = MutableLiveData<String?>()
    val userEmail: LiveData<String?> = _userEmail

    fun setToastMsg(Msg: String) {
        clearToastMsg()
        _toastMessage.value = Msg
    }
    fun clearToastMsg() {
        _toastMessage.value = ""
    }
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

    fun createUser(name: String) = liveData(Dispatchers.IO) {
        repository.createUserInFireStore(name).collect { response ->
            emit(response)
        }
    }


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

    fun isUserInfoForSignUpNotEmpty(userPassword: String, userName: String, email: String): Boolean {
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
                    return true }
        } else
            if (isEmailEmpty(email)) {
                return false
            }
        return false
    }

    // fun to Login using firebase by user email & password.
  private  fun loginUpUser(userPassword: String, email: String) {
            FirebaseModule.provideFirebaseAuthInstance()
                .signInWithEmailAndPassword(email.trim(), userPassword.trim())
                .addOnCanceledListener {
                    setToastMsg( "canceled")
                }
                .addOnFailureListener {
                    setToastMsg( it.message.toString())
                }
                .addOnSuccessListener {
                    setToastMsg("success ${it.user?.email}")

                }
    }

    // fun to sign up using firebase by user name, password and email.
    private fun signUpUser(userPassword: String, email: String, name: String) {
        FirebaseModule.provideFirebaseAuthInstance()
            .createUserWithEmailAndPassword(email.trim(), userPassword.trim())
            .addOnCanceledListener {
                setToastMsg("canceled")
            }
            .addOnFailureListener {
                setToastMsg(it.message.toString())
            }
            .addOnSuccessListener {
                setToastMsg("success ${it.user?.email}")
                createUser(name)

            }
    }

}






