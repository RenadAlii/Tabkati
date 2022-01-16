package com.example.tabkati.ui.splash

import androidx.lifecycle.ViewModel
import com.example.tabkati.repository.SplashRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository: SplashRepository
): ViewModel() {

    val isUserAuthenticated get() = repository.isUserAuthenticatedInFirebase

}