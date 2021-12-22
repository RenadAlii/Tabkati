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
    private val sharedViewModel: LoginViewModel by activityViewModels()
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
            viewModel = sharedViewModel
            sharedViewModel.setEmail(editTextTextEmailAddressLogin.editText?.text.toString())
            loginButton.setOnClickListener {
                if (sharedViewModel.loginUpUser(editTextTextPasswordLogin.editText?.text.toString())) {
                    sharedViewModel.loginUpUser(editTextTextPasswordLogin.editText?.text.toString())
                }
                Toast.makeText(requireContext(), "sign up", Toast.LENGTH_LONG).show()

            }
            // @ because inside binding.apply this revers to the binding instance.
            // not the class DetailsFragment.
            loginFragment = this@LoginFragment
        }
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