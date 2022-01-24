package com.example.tabkati.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.tabkati.R
import com.example.tabkati.databinding.ActivityMainBinding
import com.example.tabkati.databinding.ActivitySplashBinding
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

    private var _binding: ActivitySplashBinding? = null
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashBinding.inflate(layoutInflater)
        binding = _binding!!
        setContentView(binding.root)
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}