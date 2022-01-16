package com.example.tabkati.ui.recipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tabkati.adapter.RecipesAdapter
import com.example.tabkati.databinding.FragmentBookMarkedBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@InternalCoroutinesApi
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class BookMarkedFragment : Fragment() {

    private var _binding: FragmentBookMarkedBinding? = null
    private lateinit var binding: FragmentBookMarkedBinding
    private val bookMarkedViewModel by viewModels<BookMarkedViewModel>()
    private lateinit var bookMarkedRecipesRecyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentBookMarkedBinding.inflate(inflater)
        binding = _binding!!
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            bookMarkedViewModel.getBookMarkedRecipe()

        }
        binding.apply {
            // Allows Data Binding to Observe LiveData with the lifecycle of this fragment.
            lifecycleOwner = viewLifecycleOwner
            bookMarkedRecipesRecyclerView = recyclerViewOfBookmarkedRecipes


            val recipesAdapter = RecipesAdapter {
                val action =
                    BookMarkedFragmentDirections.actionBookMarkedFragmentToRecipeDetailsFragment(
                        recipeId = it.id.toString()
                    )
                this@BookMarkedFragment.findNavController().navigate(action)
            }
            recyclerViewOfBookmarkedRecipes.adapter = recipesAdapter

            lifecycleScope.launch {
                bookMarkedViewModel.getBookMarkedRecipe()
                bookMarkedViewModel.bookMarkedUIState.collect {
                    // check if the list of bookmarked recipes is empty or not
                    if (it.recipesItems.isNullOrEmpty()) {
                        // if empty display the msg.
                        textOfEmptyList.visibility = View.VISIBLE
                        recyclerViewOfBookmarkedRecipes.visibility = View.GONE
                    } else {
                        // if not empty display the list of recipes.
                        textOfEmptyList.visibility = View.GONE
                        recyclerViewOfBookmarkedRecipes.visibility = View.VISIBLE
                        recipesAdapter.submitList(it.recipesItems)
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