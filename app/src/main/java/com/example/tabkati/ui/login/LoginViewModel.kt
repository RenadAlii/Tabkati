package com.example.tabkati.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tabkati.R
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class LoginViewModel : ViewModel() {

    private var _userEmail = MutableLiveData<String?>()
    val userEmail: LiveData<String?> = _userEmail

    private var _userName = MutableLiveData<String?>()
    val userName: LiveData<String?> = _userName
    private var _isSuccess = MutableLiveData<String>()
    val isSuccess: LiveData<String> = _isSuccess

    fun setEmail(email: String) {
        _userEmail.value = email
    }

    fun setUserName(name: String) {
        _userName.value = name
    }


    // fun to check if the user Info is empty or Not for sign Up.
    private fun isUserInfoForSignUpNotEmpty(userPassword: String,username:String,email: String): Boolean {
        return email.isNotEmpty() && userPassword.isNotEmpty() && username.isNotEmpty()
    }

    // fun to check if the user Info is empty or Not for Login.
    private fun isUserInfoForLogInNotEmpty(userPassword: String): Boolean {
        return _userEmail.value!!.isNotEmpty() && userPassword.isNotEmpty()
    }


    fun signUpWithGoogle(): GoogleSignInOptions {
        return GoogleSignInOptions
          .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
          .requestIdToken("185765143475-6bniaqlrg8ejrvf0vqgi6tu4huqpnmoi.apps.googleusercontent.com")
          .requestEmail()
          .build()
    }


    // fun to sign up using firebase by user name, password and email.
    fun signUpUser(userPassword: String,email: String,name: String) {

        if (isUserInfoForSignUpNotEmpty(userPassword.trim(),name,email)) {
            FirebaseAuth
                .getInstance()
                .createUserWithEmailAndPassword(email.trim(), userPassword.trim())
                .addOnCanceledListener {
                    _isSuccess.value ="canceled"
                }
                .addOnFailureListener {
                   _isSuccess.value =it.message.toString()
                }
                .addOnSuccessListener {
                    _isSuccess.value="success ${it.user?.email}"
                }
         }

    }

    // fun to Login using firebase by user email & password.
    fun loginUpUser(userPassword: String): Boolean {
        var successful = false
        if (isUserInfoForLogInNotEmpty(userPassword)) {
            FirebaseAuth
                .getInstance()
                .signInWithEmailAndPassword(_userEmail.value!!.trim(), userPassword.trim())
                .addOnCompleteListener {

                    successful = it.isSuccessful
                    // var hold the user info.
                    val firebaseUser: FirebaseUser = it.result!!.user!!
         }
         }
        return successful
    }




}