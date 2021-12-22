package com.example.tabkati.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tabkati.R
import com.example.tabkati.data.Response
import com.example.tabkati.databinding.FragmentAuthenticationBinding
import com.example.tabkati.di.AppModule.provideGoogleSignInOptions
import com.example.tabkati.di.AppModule.provideSignInIntent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named


class AuthenticationFragment : Fragment() {


    private lateinit var auth: FirebaseAuth
    private var _binding: FragmentAuthenticationBinding? = null
    private lateinit var binding: FragmentAuthenticationBinding
    private val sharedViewModel: LoginViewModel by activityViewModels()
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
//    private val viewModel: AuthViewModel by activityViewModels()

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
////        initResultLauncher()
//    }

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
        FirebaseAuth.getInstance().signOut()
        // Initialize Firebase Auth
        auth = Firebase.auth
        auth.signOut()
        binding.apply {
            lifecycleOwner = lifecycleOwner
            viewModel = sharedViewModel
            // @ because inside binding.apply this revers to the binding instance.
            // not the class AuthenticationFragment.
            authenticationFragment = this@AuthenticationFragment
            googlePlusIcon.setOnClickListener {
                //resultLauncher.launch(provideSignInIntent(GoogleSignIn.getClient(requireContext(), provideGoogleSignInOptions())))

            }

        }
    }



//private fun initResultLauncher() {
//    resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//        if (result.resultCode == AppCompatActivity.RESULT_OK) {
//            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
//            try {
//                val googleSignInAccount = task.getResult(ApiException::class.java)
//                googleSignInAccount?.apply {
//                    idToken?.let { idToken ->
//                        signInWithGoogle(idToken)
//                    }
//                }
//            } catch (e: ApiException) {
//                print(e.message)
//            }
//        }
//    }
//}



//private fun signInWithGoogle(idToken: String) {
//    viewModel.signInWithGoogle(idToken).observe(viewLifecycleOwner) { response ->
//        when (response) {
//            is Response.Loading -> binding.progressBar.visibility = View.VISIBLE
//            is Response.Success<*> -> {
//                val isNewUser = response.data
//                if (isNewUser) {
//                    createUser()
//                } else {
//                    // goToMainActivity()
//                    binding.progressBar.visibility = View.GONE
//                }
//            }
//            is Response.Failure -> {
//                print(response.errorMessage)
//                binding.progressBar.visibility = View.GONE
//            }
//        }
//    }
//}

//private fun createUser() {
//    viewModel.createUser().observe(viewLifecycleOwner, { response ->
//        when(response) {
//            is Response.Loading -> binding.progressBar.visibility = View.VISIBLE
//            is Response.Success -> {
//               // goToMainActivity()
//                binding.progressBar.visibility = View.GONE
//            }
//            is Response.Failure -> {
//                Toast.makeText(requireContext(), response.errorMessage, Toast.LENGTH_SHORT).show()
//                binding.progressBar.visibility = View.GONE
//            }
//        }
//    })
//}
    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }


    // fun to go to Login.
    fun goToLoginFragment() {
        findNavController().navigate(R.id.action_authenticationFragment_to_loginFragment)
    }

    // fun to go to SignUp.
    fun goToSignUpFragment() {
        findNavController().navigate(R.id.action_authenticationFragment_to_signUpFragment)
    }


    companion object {
        private const val TAG = "GoogleActivity"
        private const val RC_SIGN_IN = 9001
    }
}