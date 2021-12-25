package com.example.tabkati.ui.recipes

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.tabkati.R
import com.example.tabkati.data.Response
import com.example.tabkati.databinding.FragmentHomeBinding
import com.example.tabkati.databinding.FragmentLoginBinding
import com.example.tabkati.ui.login.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var binding: FragmentHomeBinding
    private val viwModel: HomeViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
        getAuthState()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater)
        binding = _binding!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            // Allows Data Binding to Observe LiveData with the lifecycle of this fragment.
            //lifecycleOwner = lifecycleOwner
            // @ because inside binding.apply this revers to the binding instance.
            // not the class HomeFragment.
            // homeFragment = this@HomeFragment
            //viewModel = sharedViewModel
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_app_bar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.authenticationFragment -> signOut()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun signOut() {
        viwModel.sigOut().observe(viewLifecycleOwner, {
            when (it) {
                is Response.Loading -> binding.progressBar.visibility = View.VISIBLE
                is Response.Success -> binding.progressBar.visibility = View.GONE
                is Response.Failure -> {
                    Log.e("renad", "signOut: ${it.errorMessage}")
                    binding.progressBar.visibility = View.GONE
                }

            }
        })
    }

    // fun to get the user authentication state so no user not authenticated will stay in home fragment.
    private fun getAuthState(){
        viwModel.getAuthState().observe(viewLifecycleOwner,{
          // if the user signedOut it = true go to Auth Activity to signIn in.
            if(it){
                goToAuthActivity()
            }
        })
    }

    private fun goToAuthActivity() {
       findNavController().navigate(R.id.action_homeFragment_to_authMainActivity)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
