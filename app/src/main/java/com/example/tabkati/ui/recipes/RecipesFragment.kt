package com.example.tabkati.ui.recipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tabkati.R
import com.example.tabkati.adapter.RecipeCategoriesAdapter
import com.example.tabkati.adapter.RecipesAdapter
import com.example.tabkati.databinding.FragmentHomeBinding
import com.example.tabkati.databinding.FragmentRecipesBinding
import com.example.tabkati.utils.Constants.CATEGORYID
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class RecipesFragment : Fragment() {
    private var _binding: FragmentRecipesBinding? = null
    private lateinit var binding: FragmentRecipesBinding
    private lateinit var recyclerViewOfRecipes: RecyclerView


    private val recipesViewModel by viewModels<RecipesViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentRecipesBinding.inflate(inflater)
        binding = _binding!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        arguments?.let {
            // set the chosen category.
            recipesViewModel.setCategoryId(it.getString(CATEGORYID).toString())
        }


        binding.apply {
            // Allows Data Binding to Observe LiveData with the lifecycle of this fragment.
            lifecycleOwner = viewLifecycleOwner
            // @ because inside binding.apply this revers to the binding instance.
            // not the class RecipesFragment.
            recipesFragment = this@RecipesFragment
            viewModel = recipesViewModel
            recyclerViewOfRecipes = recyclerViewOfRandomRecipes
            recyclerViewOfRecipes.layoutManager = LinearLayoutManager(requireContext())



            val recipesAdapter = RecipesAdapter {
                val action = RecipesFragmentDirections.actionRecipesFragmentToRecipeDetailsFragment(
                    recipeId = it.id.toString()
                )
                this@RecipesFragment.findNavController().navigate(action)
            }
            recyclerViewOfRecipes.adapter = recipesAdapter



        }



        }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}