package com.example.tabkati.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.tabkati.R
import com.example.tabkati.utils.Constants.AUTH_INTENT
import com.example.tabkati.utils.Constants.MAIN_INTENT
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named


@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    @Named(AUTH_INTENT)
    @Inject
    lateinit var authIntent: Intent

    @Named(MAIN_INTENT)
    @Inject
    lateinit var mainIntent: Intent

    private val viewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        lifecycleScope.launch {
            delay(3000)
        checkIfUserIsAuthenticated()
        }
    }

    private fun checkIfUserIsAuthenticated() {
        if (viewModel.isUserAuthenticated) {
            goToMainActivity()

        } else {
            goToAuthInActivity()
        }
    }

    private fun goToMainActivity() {
        startActivity(mainIntent)
        finish()
    }

    private fun goToAuthInActivity() {
        startActivity(authIntent)
        finish()
    }
}