package com.example.tabkati.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.tabkati.R
import com.example.tabkati.databinding.ActivityAuthMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthMainActivity : AppCompatActivity() {
    private var _binding: ActivityAuthMainBinding? = null
    private lateinit var binding: ActivityAuthMainBinding

    //create the navController using the navHostFragment.
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAuthMainBinding.inflate(layoutInflater)
        binding = _binding!!
        setContentView(binding.root)

        //get navHost fragment from this Activity
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_for_auth_main) as NavHostFragment
        navController = navHostFragment.navController

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }
}