package com.example.tabkati.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.tabkati.R
import com.example.tabkati.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private var _binding: ActivityRegisterBinding? = null
    private lateinit var binding: ActivityRegisterBinding
    private val model: LoginViewModel by  viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        binding = _binding!!
        setContentView(binding.root)
        binding.apply {
            // Allows Data Binding to Observe LiveData with the lifecycle of this Activity.
            // @ because inside binding.apply this revers to the binding instance.
            // not the class DetailsFragment.
            registerActivity = this@RegisterActivity
            lifecycleOwner = lifecycleOwner
            viewModel = model
            model.setEmail(editTextTextEmailAddressSignup.editText?.text.toString())
            model.setUserName(personNameText.editText?.text.toString())
            signup.setOnClickListener {
                if( model.signUpUser(editTextTextPasswordSignUp.editText?.text.toString())){
                    Toast.makeText(this@RegisterActivity,"sign up", Toast.LENGTH_LONG).show()
                }else{

                    Toast.makeText(this@RegisterActivity,"error", Toast.LENGTH_LONG).show()

                }

            }



        }

    }

    // fun to go to the Login Activity.
    fun goToLoginActivity(){
        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }
}
