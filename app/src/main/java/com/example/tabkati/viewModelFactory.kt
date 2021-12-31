package com.example.tabkati

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tabkati.di.Servicelocator
import com.example.tabkati.ui.login.AuthViewModel

class ViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AuthViewModel(Servicelocator.provideAuthRepo()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}
