package com.example.tabkati.ui.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.tabkati.databinding.FragmentModalBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class ModalBottomSheet : BottomSheetDialogFragment() {

    private var _binding: FragmentModalBottomSheetBinding? = null
    private lateinit var binding: FragmentModalBottomSheetBinding

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





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            profile.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToProfileFragment()
              findNavController().navigate(action)
                dismiss()
            }

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}