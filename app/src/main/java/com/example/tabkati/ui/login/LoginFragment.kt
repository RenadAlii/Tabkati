package com.example.tabkati.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.tabkati.R
import com.example.tabkati.databinding.FragmentLoginBinding



class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private lateinit var binding: FragmentLoginBinding
    private val sharedViewModel: AuthViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater)
        binding = _binding!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            // Allows Data Binding to Observe LiveData with the lifecycle of this Activity.
            lifecycleOwner = lifecycleOwner
            // @ because inside binding.apply this revers to the binding instance.
            // not the class loginFragment.
            loginFragment = this@LoginFragment
            viewModel = sharedViewModel
            loginButton.setOnClickListener {
                if (sharedViewModel.isUserInfoForLogInNotEmpty(
                        editTextTextPasswordLogin.editText?.text.toString(),
                        editTextTextEmailAddressLogin.editText?.text.toString()
                    )
                ) {
                    makeToast()
                }else{
                sharedViewModel.setToastMsg(getString(R.string.enter_all_info))
                enableErrorTextField()
                makeToast()
            }}

        }
    }

    private fun makeToast() {
        sharedViewModel.toastMessage.observe(viewLifecycleOwner, {
            it?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }

        })
    }

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


    // fun to go to SignUp.
    fun goToSignUpFragment() {
        findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}