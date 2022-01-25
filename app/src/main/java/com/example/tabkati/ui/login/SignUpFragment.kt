package com.example.tabkati.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.tabkati.MainActivity
import com.example.tabkati.R
import com.example.tabkati.databinding.FragmentSignUpBinding
import com.example.tabkati.utils.RecipesApiStatus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private lateinit var binding: FragmentSignUpBinding
    private val sharedViewModel by viewModels<AuthViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSignUpBinding.inflate(inflater)
        binding = _binding!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            signUpFragment = this@SignUpFragment
            lifecycleOwner = viewLifecycleOwner

            signup.setOnClickListener {
                disableErrorTextField()
                if (!sharedViewModel.isUserInfoForSignUpNotEmpty(
                        editTextTextPasswordSignUp.editText?.text.toString(),
                        personNameText.editText?.text.toString(),
                        editTextTextEmailAddressSignup.editText?.text.toString()
                    )
                ) {
                    makeToast(getString(R.string.enter_all_info))
                    enableErrorTextField()

                } else {
                    authUiState()
                }
            }


        }

        }




    // fun that collects the auth UIState Screen.
    private fun authUiState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                sharedViewModel.authUIState.collect { uistatus ->
                    when (uistatus.status) {
                        // in case of loading.
                        RecipesApiStatus.LOADING -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        // in case of error.
                        RecipesApiStatus.ERROR -> {
                            binding.progressBar.visibility = View.GONE
                            makeToast(uistatus.msg)

                        }
                        // in case of done.
                        RecipesApiStatus.DONE -> {
                            binding.progressBar.visibility = View.GONE
                            goToMainActivity()


                        }
                    }
                }
            }


        }
    }

    // fun to make toast.
    private fun makeToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }



    // fun to disable error text filed.
    private fun disableErrorTextField() {
        binding.apply {
            // disable error filed for password field.
            editTextTextPasswordSignUp.isErrorEnabled = false
            editTextTextPasswordSignUp.error = ""
            // disable error filed for personal name field.
            personNameText.isErrorEnabled = false
            personNameText.error = ""
            // disable error filed for email field.
            editTextTextEmailAddressSignup.isErrorEnabled = false
            editTextTextEmailAddressSignup.error = ""
        }
    }



    // fun to enable error text filed.
    private fun enableErrorTextField() {
        binding.apply {
            setErrorTextFieldForPassword()
            setErrorTextFieldForUserName()
            setErrorTextFieldForEmail()
            setErrorTextFieldForPasswordAndUserName()
            setErrorTextFieldForPasswordAndEmail()
            setErrorTextFieldForUserNameAndEmail()
            setErrorTextFieldForUserNameAndEmailAndPassword()
        }
    }

    private fun FragmentSignUpBinding.setErrorTextFieldForUserNameAndEmail() {
        sharedViewModel.errorEnableMsg.observe(viewLifecycleOwner, {
            if (it == "nameemail") {
                // enable error filed for personal name field.
                personNameText.isErrorEnabled = true
                personNameText.error = getString(R.string.user_name_is_required)
                // enable error filed for email field.
                editTextTextEmailAddressSignup.isErrorEnabled = true
                editTextTextEmailAddressSignup.error = getString(R.string.email_is_required)
            }

        })
    }

    private fun FragmentSignUpBinding.setErrorTextFieldForUserNameAndEmailAndPassword() {
        sharedViewModel.errorEnableMsg.observe(viewLifecycleOwner, {

            if (it == "passwordnameemail") {
                // enable error filed for password field.
                editTextTextPasswordSignUp.isErrorEnabled = true
                editTextTextPasswordSignUp.error = getString(R.string.password_is_required)
                // enable error filed for personal name field.
                personNameText.isErrorEnabled = true
                personNameText.error = getString(R.string.user_name_is_required)
                // enable error filed for email field.
                editTextTextEmailAddressSignup.isErrorEnabled = true
                editTextTextEmailAddressSignup.error = getString(R.string.email_is_required)
            }

        })
    }

    private fun FragmentSignUpBinding.setErrorTextFieldForPasswordAndEmail() {
        sharedViewModel.errorEnableMsg.observe(viewLifecycleOwner, {
            if (it == "passwordemail") {
                // enable error filed for password field.
                editTextTextPasswordSignUp.isErrorEnabled = true
                editTextTextPasswordSignUp.error = getString(R.string.password_is_required)
                // enable error filed for email field.
                editTextTextEmailAddressSignup.isErrorEnabled = true
                editTextTextEmailAddressSignup.error = getString(R.string.email_is_required)
            }
        })
    }

    private fun FragmentSignUpBinding.setErrorTextFieldForPasswordAndUserName() {
        sharedViewModel.errorEnableMsg.observe(viewLifecycleOwner, {
            if (it == "passwordname") {
                // enable error filed for password field.
                editTextTextPasswordSignUp.isErrorEnabled = true
                editTextTextPasswordSignUp.error = getString(R.string.password_is_required)
                // enable error filed for personal name field.
                personNameText.isErrorEnabled = true
                personNameText.error = getString(R.string.user_name_is_required)
            }
        })
    }


    private fun FragmentSignUpBinding.setErrorTextFieldForEmail() {
        sharedViewModel.errorEnableMsg.observe(viewLifecycleOwner, {
            if (it == "email") {
                editTextTextEmailAddressSignup.isErrorEnabled = true
                editTextTextEmailAddressSignup.error = getString(R.string.email_is_required)
            }
        })
    }

    private fun FragmentSignUpBinding.setErrorTextFieldForUserName() {
        sharedViewModel.errorEnableMsg.observe(viewLifecycleOwner, {
            if (it == "name") {
                personNameText.isErrorEnabled = true
                personNameText.error = getString(R.string.user_name_is_required)
            }
        })
    }

    private fun FragmentSignUpBinding.setErrorTextFieldForPassword() {
        sharedViewModel.errorEnableMsg.observe(viewLifecycleOwner, {
            if (it == "password") {
                editTextTextPasswordSignUp.isErrorEnabled = true
                editTextTextPasswordSignUp.error = getString(R.string.password_is_required)
            }
        })
    }



    // fun to go to Login.
    fun goToLoginFragment() {
        findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
    }


    private fun goToMainActivity() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}