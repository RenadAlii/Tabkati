package com.example.tabkati.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tabkati.di.FirebaseModule.provideFirebaseAuthInstance



class LoginViewModel : ViewModel() {

    private var _userEmail = MutableLiveData<String?>()
    val userEmail: LiveData<String?> = _userEmail

    private var _userName = MutableLiveData<String?>()
    val userName: LiveData<String?> = _userName
    private var _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> = _toastMessage

    fun setEmail(email: String) {
        _userEmail.value = email
    }



    // fun to check if the user Info is empty or Not for sign Up.
    private fun isUserInfoForSignUpNotEmpty(userPassword: String,username:String,email: String): Boolean {
        return email.isNotEmpty() && userPassword.isNotEmpty() && username.isNotEmpty()
    }

    // fun to check if the user Info is empty or Not for Login.
    private fun isUserInfoForLogInNotEmpty(userPassword: String): Boolean {
        return _userEmail.value!!.isNotEmpty() && userPassword.isNotEmpty()
    }




    // fun to sign up using firebase by user name, password and email.
    fun signUpUser(userPassword: String,email: String,name: String) {

        if (isUserInfoForSignUpNotEmpty(userPassword.trim(),name,email)) {
            provideFirebaseAuthInstance()
                .createUserWithEmailAndPassword(email.trim(), userPassword.trim())
                .addOnCanceledListener {
                    _toastMessage.value ="canceled"
                }
                .addOnFailureListener {
                   _toastMessage.value =it.message.toString()
                }
                .addOnSuccessListener {
                    _toastMessage.value="success ${it.user?.email}"
                }
         }

    }

//    // fun to Login using firebase by user email & password.
//    fun loginUpUser(userPassword: String): Boolean {
//        var successful = false
//            provideFirebaseAuthInstance()
//                .signInWithEmailAndPassword(_userEmail.value!!.trim(), userPassword.trim())
//                .addOnCompleteListener {
//
//                    successful = it.isSuccessful
//                    // var hold the user info.
//                    val firebaseUser: FirebaseUser = it.result!!.user!!
//         }
//         }
//        return successful
//    }
//



}