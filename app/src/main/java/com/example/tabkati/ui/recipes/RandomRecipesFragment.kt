package com.example.tabkati.ui.recipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tabkati.adapter.RecipesAdapter
import com.example.tabkati.databinding.FragmentRandomRecipesBinding
import com.example.tabkati.utils.lottieAnimationStatus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class RandomRecipesFragment : Fragment() {

    private var _binding: FragmentRandomRecipesBinding? = null
    private lateinit var binding: FragmentRandomRecipesBinding
    private lateinit var recyclerViewOfRecipes: RecyclerView
    private val recipesViewModel by viewModels<RandomRecipesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRandomRecipesBinding.inflate(inflater)
        binding = _binding!!
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recipesViewModel.getRandomRecipes()

        binding.apply {
            // Allows Data Binding to Observe LiveData with the lifecycle of this fragment.
            lifecycleOwner = viewLifecycleOwner
            viewModel = recipesViewModel
            recyclerViewOfRecipes = recyclerViewOfRandomRecipes
            recyclerViewOfRecipes.layoutManager = LinearLayoutManager(requireContext())


            val recipesAdapter = RecipesAdapter {
                // navigate and send the id of the recipe to RecipeDetailsFragment.
                val action = RandomRecipesFragmentDirections.actionRandomRecipesFragmentToRecipeDetailsFragment(
                    recipeId = it.id.toString()
                )
                this@RandomRecipesFragment.findNavController().navigate(action)
            }
            recyclerViewOfRecipes.adapter = recipesAdapter

            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    recipesViewModel.respicesUIState.collect { uistatus ->
                        binding.apply {
                            progressBar.lottieAnimationStatus(progressBar,
                                progressBarError,
                                uistatus.status,
                                recyclerViewOfRandomRecipes)
                        }
                    }
                }

            }



        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}