package com.example.tabkati.ui.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.tabkati.R
import com.example.tabkati.data.Response
import com.example.tabkati.databinding.FragmentSignUpBinding


class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private lateinit var binding: FragmentSignUpBinding
    private val sharedViewModel: AuthViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSignUpBinding.inflate(inflater)
        binding = _binding!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            signUpFragment = this@SignUpFragment
            lifecycleOwner = lifecycleOwner

            signup.setOnClickListener {
                disableErrorTextField()
                if (!sharedViewModel.isUserInfoForSignUpNotEmpty(
                        editTextTextPasswordSignUp.editText?.text.toString(),
                        personNameText.editText?.text.toString(),
                        editTextTextEmailAddressSignup.editText?.text.toString()
                    )
                ) {
                    sharedViewModel.setToastMsg(getString(R.string.enter_all_info))
                    enableErrorTextField()
                    makeToast()
                }else {
                    makeToast()
                    createUser(personNameText.editText?.text.toString())
                }            }




        }
    }

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

    // fun to make Toast
    private fun makeToast() {
        sharedViewModel.toastMessage.observe(viewLifecycleOwner, {
            it?.let {
              if (it != ""){
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }}

        })
    }

    // fun to go to Login.
    fun goToLoginFragment() {
        findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
    }
    private fun createUser(name: String) {
        sharedViewModel.createUser(name).observe(viewLifecycleOwner, { response ->
            when (response) {
                is Response.Loading -> binding.progressBar.visibility = View.VISIBLE
                is Response.Success -> {
                   goToMainActivity()
                    binding.progressBar.visibility = View.GONE
                }
                is Response.Failure -> {
                    Toast.makeText(requireContext(), response.errorMessage, Toast.LENGTH_SHORT)
                        .show()
                    binding.progressBar.visibility = View.GONE
                }
            }
        })
    }

    private fun goToMainActivity() {
        findNavController().navigate(R.id.action_signUpFragment_to_mainActivity)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }
}