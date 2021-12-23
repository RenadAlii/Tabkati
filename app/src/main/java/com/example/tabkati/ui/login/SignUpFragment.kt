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
import com.example.tabkati.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint


class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private lateinit var binding: FragmentSignUpBinding
    private val sharedViewModel: LoginViewModel by activityViewModels()



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
            sharedViewModel.setEmail(editTextTextEmailAddressSignup.editText?.text.toString())
            sharedViewModel.setUserName(personNameText.editText?.text.toString())
            signup.setOnClickListener {
                sharedViewModel.signUpUser(
                    editTextTextPasswordSignUp.editText?.text.toString(),
                    editTextTextEmailAddressSignup.editText?.text.toString(),
                    personNameText.editText?.text.toString()
                )


            }
            sharedViewModel.isSuccess.observe(viewLifecycleOwner, {
                it?.let {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                }

            })


        }
    }

    // fun to go to Login.
    fun goToLoginFragment() {
        findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }
}