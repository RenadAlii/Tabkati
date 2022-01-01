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
import com.example.tabkati.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private lateinit var binding: FragmentSearchBinding
    private val searchViwModel by viewModels<SearchViewModel>()
    private lateinit var recyclerViewOfRecipe: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater)
        binding = _binding!!
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            // Allows Data Binding to Observe LiveData with the lifecycle of this fragment.
            lifecycleOwner = viewLifecycleOwner
            // @ because inside binding.apply this revers to the binding instance.
            // not the class SearchFragment.
            searchFragment = this@SearchFragment
            viewModel = searchViwModel


            recyclerViewOfRecipe = recyclerViewOfRecipes
            recyclerViewOfRecipe.layoutManager = LinearLayoutManager(requireContext())
            val recipesAdapter = RecipesAdapter({ recipe ->
                // navigate and send the id of the cat to display the list of recipes by cat.
                val action = SearchFragmentDirections.actionSearchFragmentToRecipeDetailsFragment(
                    recipeId = recipe.id.toString()
                )
                this@SearchFragment.findNavController().navigate(action)
            })
            recyclerViewOfRecipe.adapter = recipesAdapter


        }}





    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}