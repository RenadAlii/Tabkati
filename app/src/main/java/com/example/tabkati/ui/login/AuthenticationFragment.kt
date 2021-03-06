package com.example.tabkati.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tabkati.MainActivity
import com.example.tabkati.R
import com.example.tabkati.utils.State.Failure
import com.example.tabkati.utils.State.Loading
import com.example.tabkati.utils.State.Success
import com.example.tabkati.databinding.FragmentAuthenticationBinding
import com.example.tabkati.di.AppModule.provideGoogleSignInOptions
import com.example.tabkati.di.AppModule.provideSignInIntent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi


@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@AndroidEntryPoint
class AuthenticationFragment : Fragment() {


    private var _binding: FragmentAuthenticationBinding? = null
    private lateinit var binding: FragmentAuthenticationBinding

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private val authviewModel by viewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initResultLauncher()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAuthenticationBinding.inflate(inflater)
        binding = _binding!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = authviewModel
            // @ because inside binding.apply this revers to the binding instance.
            // not the class AuthenticationFragment.
            authenticationFragment = this@AuthenticationFragment
            googlePlusIcon.setOnClickListener {
                progressBar.visibility = View.VISIBLE
               resultLauncher.launch(provideSignInIntent(GoogleSignIn.getClient(requireContext(), provideGoogleSignInOptions())))
                progressBar.visibility = View.GONE

            }

        }
    }





    // fun to observe the response of signInWithGoogle.
    private fun signInWithGoogle(idToken: String) {
        authviewModel.signInWithGoogle(idToken).observe(this) { response ->
            when (response) {
                is Loading -> binding.progressBar.visibility = View.VISIBLE
                is Success -> {
                    val isNewUser = response.data
                    // if the user in new create register the user in fireStore else go to main Activity.
                    if (isNewUser) {
                        createUser()
                    } else {
                        goToMainActivity()
                        binding.progressBar.visibility = View.GONE
                    }
                }
                is Failure -> {
                    Toast.makeText(requireContext(), response.errorMessage, Toast.LENGTH_SHORT)
                        .show()
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }

    // fun to observe the response of creating new user when log in with google.
    private fun createUser() {
        authviewModel.createUser().observe(viewLifecycleOwner) { response ->
            when (response) {
                is Loading -> binding.progressBar.visibility = View.VISIBLE
                is Success -> {
                    goToMainActivity()
                    binding.progressBar.visibility = View.GONE
                }
                is Failure -> {
                    Toast.makeText(requireContext(), response.errorMessage, Toast.LENGTH_SHORT)
                        .show()
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }


    private fun initResultLauncher() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == AppCompatActivity.RESULT_OK) {
                    val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                    try {
                        val googleSignInAccount = task.getResult(ApiException::class.java)
                        googleSignInAccount?.apply {
                            idToken?.let { idToken ->
                                signInWithGoogle(idToken)
                            }
                        }
                    } catch (e: ApiException) {
                        print(e.message)
                    }
                }
            }
    }

    // fun to go to main activity.
    private fun goToMainActivity() {

        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
    }



    // fun to go to Login.
    fun goToLoginFragment() {
        findNavController().navigate(R.id.action_authenticationFragment_to_loginFragment)
    }

    // fun to go to SignUp.
    fun goToSignUpFragment() {
        findNavController().navigate(R.id.action_authenticationFragment_to_signUpFragment)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}