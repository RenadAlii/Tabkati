package com.example.tabkati.ui.recipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.tabkati.R
import com.example.tabkati.databinding.FragmentHomeBinding
import com.example.tabkati.databinding.FragmentProfileBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private lateinit var binding: FragmentProfileBinding
    private val userInfoViewModel by viewModels<UserInfoViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater)
        binding = _binding!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            // Allows Data Binding to Observe LiveData with the lifecycle of this fragment.
            lifecycleOwner = viewLifecycleOwner
            userViewmodel = userInfoViewModel

            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    userInfoViewModel.profileUIState.collect {
                        textInputName.setText(it.name)

                    }}}
                    saveButton.setOnClickListener {
                        userInfoViewModel.setUserName(textInputName.text.toString())
                        Snackbar.make(view, "your data updated", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                    }


                }
            }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}