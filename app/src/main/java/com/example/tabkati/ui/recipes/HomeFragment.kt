package com.example.tabkati.ui.recipes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tabkati.R
import com.example.tabkati.data.Response
import com.example.tabkati.databinding.FragmentHomeBinding
import com.example.tabkati.ui.login.AuthMainActivity
import com.example.tabkati.ui.login.AuthViewModel

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var binding: FragmentHomeBinding
   // private val homeViwModel: HomeViewModel by activityViewModels()
    private val homeViwModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater)
        binding = _binding!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            // Allows Data Binding to Observe LiveData with the lifecycle of this fragment.
            lifecycleOwner = viewLifecycleOwner
            // @ because inside binding.apply this revers to the binding instance.
            // not the class HomeFragment.
            homeFragment = this@HomeFragment
            viewModel = homeViwModel
        }

        getAuthState()
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
        homeViwModel.sigOut().observe(viewLifecycleOwner, {
            when (it) {
                is Response.Loading -> binding.progressBar.visibility = View.VISIBLE
                is Response.Success -> binding.progressBar.visibility = View.GONE
                is Response.Failure -> {
                    binding.progressBar.visibility = View.GONE
                }

            }
        })
    }

    // fun to get the user authentication state so no user not authenticated will stay in home fragment.
    private fun getAuthState() {
        homeViwModel.getAuthState().observe(viewLifecycleOwner, {
            // if the user signedOut it = true go to Auth Activity to signIn in.
            if (it) {
                goToAuthActivity()
            }
        })
    }

    private fun goToAuthActivity() {
        val intent = Intent(requireContext(), AuthMainActivity::class.java)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
