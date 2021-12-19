package com.example.tabkati.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.tabkati.R
import com.example.tabkati.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private var _binding: ActivityLoginBinding? = null
    private lateinit var binding: ActivityLoginBinding
    private val model: LoginViewModel by  viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        binding = _binding!!
        setContentView(binding.root)
        binding.apply {
            // Allows Data Binding to Observe LiveData with the lifecycle of this Activity.
            lifecycleOwner = lifecycleOwner
            viewModel = model
            model.setEmail(editTextTextEmailAddressLogin.editText?.text.toString())
            loginButton.setOnClickListener {
                if (                model.loginUpUser(editTextTextPasswordLogin.editText?.text.toString())){
                    model.loginUpUser(editTextTextPasswordLogin.editText?.text.toString())}
                Toast.makeText(this@LoginActivity,"sign up", Toast.LENGTH_LONG).show()

            }
            // @ because inside binding.apply this revers to the binding instance.
            // not the class DetailsFragment.
            loginActivity = this@LoginActivity
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}