package com.example.tabkati.ui.recipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tabkati.R
import com.example.tabkati.databinding.FragmentBookMarkedBinding
import com.example.tabkati.databinding.FragmentGroceriesBinding


class GroceriesFragment : Fragment() {


    private var _binding: FragmentGroceriesBinding? = null
    private lateinit var binding: FragmentGroceriesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentGroceriesBinding.inflate(inflater)
        binding = _binding!!
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            // Allows Data Binding to Observe LiveData with the lifecycle of this fragment.
//            lifecycleOwner = viewLifecycleOwner
//            userViewmodel = userInfoViewModel


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}