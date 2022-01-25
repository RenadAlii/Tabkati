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
import com.example.tabkati.databinding.FragmentLoginBinding
import com.example.tabkati.utils.RecipesApiStatus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private lateinit var binding: FragmentLoginBinding
    private val sharedViewModel by viewModels<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater)
        binding = _binding!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment.
            lifecycleOwner = viewLifecycleOwner
            // @ because inside binding.apply this revers to the binding instance.
            // not the class loginFragment.
            loginFragment = this@LoginFragment
            viewModel = sharedViewModel


            loginButton.setOnClickListener {
                disableErrorTextField()

                if (!sharedViewModel.isUserInfoForLogInNotEmpty(
                        editTextTextPasswordLogin.editText?.text.toString(),
                        editTextTextEmailAddressLogin.editText?.text.toString()
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

    // fun to disable error text filed.
    private fun disableErrorTextField() {
        binding.apply {
            // disable error filed for email field.
            editTextTextEmailAddressLogin.isErrorEnabled = false
            editTextTextEmailAddressLogin.error = ""

            // disable error filed for password field.
            editTextTextPasswordLogin.isErrorEnabled = false
            editTextTextPasswordLogin.error = ""
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


    // fun to enable error text filed.
    private fun enableErrorTextField() {
        binding.apply {
            setErrorTextFieldForPassword()
            setErrorTextFieldForEmail()
            setErrorTextFieldForPasswordAndEmail()

        }
    }

    private fun FragmentLoginBinding.setErrorTextFieldForPasswordAndEmail() {
        sharedViewModel.errorEnableMsg.observe(viewLifecycleOwner, {
            if (it == "passwordemail") {
                // enable error filed for password field.
                editTextTextPasswordLogin.isErrorEnabled = true
                editTextTextPasswordLogin.error = getString(R.string.password_is_required)
                // enable error filed for email field.
                editTextTextEmailAddressLogin.isErrorEnabled = true
                editTextTextEmailAddressLogin.error = getString(R.string.email_is_required)
            }
        })
    }

    private fun FragmentLoginBinding.setErrorTextFieldForEmail() {
        sharedViewModel.errorEnableMsg.observe(viewLifecycleOwner, {
            if (it == "email") {
                // enable error filed for email field.
                editTextTextEmailAddressLogin.isErrorEnabled = true
                editTextTextEmailAddressLogin.error = getString(R.string.email_is_required)
            }
        })
    }

    private fun FragmentLoginBinding.setErrorTextFieldForPassword() {
        sharedViewModel.errorEnableMsg.observe(viewLifecycleOwner, {
            if (it == "password") {
                // enable error filed for password field.
                editTextTextPasswordLogin.isErrorEnabled = true
                editTextTextPasswordLogin.error = getString(R.string.password_is_required)
            }
        })
    }


    // fun to go to SignUp fragment.
    fun goToSignUpFragment() {
        findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
    }

    // fun to go the main activity.
    private fun goToMainActivity() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}