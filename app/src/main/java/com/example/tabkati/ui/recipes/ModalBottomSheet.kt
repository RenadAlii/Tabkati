package com.example.tabkati.ui.recipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tabkati.R
import com.example.tabkati.adapter.RecipesAdapter
import com.example.tabkati.databinding.FragmentModalBottomSheetBinding
import com.example.tabkati.databinding.FragmentRecipesBinding
import com.example.tabkati.utils.Constants
import android.widget.Toast

import com.example.tabkati.MainActivity
import com.example.tabkati.databinding.FragmentSearchBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class ModalBottomSheet : BottomSheetDialogFragment() {

    private var _binding: FragmentModalBottomSheetBinding? = null
    private lateinit var binding: FragmentModalBottomSheetBinding
    private lateinit var languages: Array<String>
    override fun getTheme(): Int = R.style.Theme_AppBottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentModalBottomSheetBinding.inflate(inflater)
        binding = _binding!!
        return binding.root
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }

    override fun onResume() {
        super.onResume()
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, languages)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        languages = resources.getStringArray(R.array.language_array)

        binding.apply {

            profile.setOnClickListener {
                Toast.makeText(requireContext(), "go to profile", Toast.LENGTH_LONG).show()
            }
            autoCompleteTextView.onItemClickListener =
                OnItemClickListener { parent, view, position, id ->

                    when (languages[position]) {
                        "Arabic" -> {
                            Toast.makeText(requireContext(), "Arabic", Toast.LENGTH_LONG).show()

                        }
                        "English" -> {
                            Toast.makeText(requireContext(), "English", Toast.LENGTH_LONG).show()
                        }
                    }
                }

        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}