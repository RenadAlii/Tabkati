package com.example.tabkati.ui.recipes

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.FOCUSABLE
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.SearchView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tabkati.R
import com.example.tabkati.adapter.RecipeCategoriesAdapter
import com.example.tabkati.adapter.RecipesAdapter
import com.example.tabkati.adapter.RecipesSearchAdapter
import com.example.tabkati.databinding.FragmentHomeBinding
import com.example.tabkati.databinding.FragmentSearchBinding
import com.example.tabkati.utils.RecipesApiStatus
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private lateinit var binding: FragmentSearchBinding
    private val searchViwModel by viewModels<SearchViewModel>()
    private lateinit var recyclerViewOfRecipe: RecyclerView
    lateinit var searchView: SearchView


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


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            // Allows Data Binding to Observe LiveData with the lifecycle of this fragment.
            lifecycleOwner = viewLifecycleOwner
            // @ because inside binding.apply this revers to the binding instance.
            // not the class SearchFragment.
            searchFragment = this@SearchFragment


            // recyclerView & adapter of recipes search.
            recyclerViewOfRecipe = recyclerViewOfRecipesSearch
            recyclerViewOfRecipe.layoutManager = LinearLayoutManager(requireContext())
            val recipesAdapter = RecipesSearchAdapter { recipe ->
                // navigate and send the id of the cat to display the list of recipes by cat.
                val action = SearchFragmentDirections.actionSearchFragmentToRecipeDetailsFragment(
                    recipeId = recipe.id.toString()
                )
                this@SearchFragment.findNavController().navigate(action)
            }

            searchViwModel.recipesList.observe(viewLifecycleOwner,
                { data ->
                    if (data == null) {
                        searchViwModel.setSearchState(RecipesApiStatus.ERROR)
                    } else {
                        recipesAdapter.submitList(data)
                        recyclerViewOfRecipesSearch.visibility = View.VISIBLE


                    }
                })
            recyclerViewOfRecipe.adapter = recipesAdapter


            searchView = searchBar

            searchView.onActionViewExpanded()
            searchView.focusable = FOCUSABLE




            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    searchViwModel.setSearchQuery(query)
                    searchViwModel.recipesList.observe(viewLifecycleOwner,
                        { data ->
                            if (data.isNullOrEmpty()) {
                                Snackbar.make(view, "No Match found", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show()
                                recyclerViewOfRecipesSearch.visibility = View.GONE
                            }  else if(query.isEmpty()){
                                recyclerViewOfRecipesSearch.visibility = View.GONE
                            }else {
                                recyclerViewOfRecipesSearch.visibility = View.VISIBLE
                            }
                        })
                    return false

                }


                override fun onQueryTextChange(newText: String): Boolean {
                    searchViwModel.setSearchQuery(newText)
                    searchViwModel.recipesList.observe(viewLifecycleOwner,
                        { data ->
                            if (data.isNullOrEmpty()) {
                                Snackbar.make(view, "No Match found", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show()
                                recyclerViewOfRecipesSearch.visibility = View.GONE
                            } else if(newText.isEmpty()){
                                recyclerViewOfRecipesSearch.visibility = View.GONE
                            } else{
                                recyclerViewOfRecipesSearch.visibility = View.VISIBLE
                            }
                        })
                    return false
                }
            })

            viewModel = searchViwModel


        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}