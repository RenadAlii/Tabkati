package com.example.tabkati.ui.recipes

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tabkati.adapter.IngredientsAdapter
import com.example.tabkati.adapter.InstructionsAdapter
import com.example.tabkati.databinding.FragmentRecipeDetailsBinding
import com.example.tabkati.utils.BookMark
import com.example.tabkati.utils.Constants.RECIPEID
import com.example.tabkati.utils.isFill
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class RecipeDetailsFragment : Fragment() {
    private var _binding: FragmentRecipeDetailsBinding? = null
    private lateinit var binding: FragmentRecipeDetailsBinding
    private lateinit var recyclerViewOfIngredient: RecyclerView
    private lateinit var recyclerViewOfInstruction: RecyclerView
    var srcUrl: String? = null
    private lateinit var recipeId: String
    private val recipeDetailsViewModel by viewModels<RecipeDetailsViewModel>()
    @InternalCoroutinesApi
    private val bookMarkedViewModel by viewModels<BookMarkedViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentRecipeDetailsBinding.inflate(inflater)
        binding = _binding!!
        return binding.root
    }

    @OptIn(InternalCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //region send arguments
        arguments?.let {
            // set the chosen recipe id .
            recipeDetailsViewModel.setRecipeId(it.getString(RECIPEID).toString())
            recipeId = it.getString(RECIPEID).toString()
        }
        //endregion


        binding.apply {
            // Allows Data Binding to Observe LiveData with the lifecycle of this fragment.
            lifecycleOwner = viewLifecycleOwner
            // @ because inside binding.apply this revers to the binding instance.
            // not the class RecipeDetailsFragment.
            recipeDetailsFragment = this@RecipeDetailsFragment
            viewModel = recipeDetailsViewModel
            recyclerViewOfIngredient = recyclerViewOfRecipeDetailsIngredients
            recyclerViewOfIngredient.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewOfInstruction = recyclerViewOfRecipeDetailsInstructions
            recyclerViewOfInstruction.layoutManager = LinearLayoutManager(requireContext())
            recipeDetailsViewModel.recipe.observe(viewLifecycleOwner, { recipe ->
                data = recipe
            })

            val recipeIngredientAdapter = IngredientsAdapter({
//action

            })

            val recipeInstructionsAdapter = InstructionsAdapter({
//action
            })
            recyclerViewOfIngredient.adapter = recipeIngredientAdapter

            recyclerViewOfInstruction.adapter = recipeInstructionsAdapter




            lifecycleScope.launch{
                repeatOnLifecycle(Lifecycle.State.RESUMED){
                    recipeDetailsViewModel.uiState .collect { recipe ->
                        recipeInstructionsAdapter.submitList(recipe.StepsItemsUiState)
                        shareIcon.setOnClickListener {
                            //fun to share the recipe
                            shareIntent(recipe)
                        }
                }
            }





                }

                val isBookMarked = bookMarkedViewModel.recipesList.observe(viewLifecycleOwner, { recipe ->
                   val recipeOrNull = recipe?.find {
                      it.id == recipeId.toInt()

                }
                     isBookmarkorNull(recipeOrNull)


                })




            bookMarkEmptyIcon.setOnClickListener {
                bookMarkEmptyIcon.isFill(bookMarkFillIcon, bookMarkEmptyIcon, BookMark.EMPTY)
                ///viewModel set the data on fire store
                bookMarkedViewModel.addBookMarkedRecipe(recipeDetailsViewModel.uiState.value)

            }
            bookMarkFillIcon.setOnClickListener {
                bookMarkEmptyIcon.isFill(bookMarkFillIcon, bookMarkEmptyIcon, BookMark.FILL)
                ///viewModel delete the data from fire store
                bookMarkedViewModel.deleteBookMarkedRecipe(recipeDetailsViewModel.uiState.value)

            }


            //region tab Listener
            tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    // Handle tab select
                    when (tab?.position) {
                        0 -> {
                            binding.recyclerViewOfRecipeDetailsIngredients.visibility = View.VISIBLE
                            binding.recyclerViewOfRecipeDetailsInstructions.visibility = View.GONE
                           // binding.card.visibility = View.GONE
                        }
                        else -> {
                            binding.recyclerViewOfRecipeDetailsIngredients.visibility = View.GONE
                            binding.recyclerViewOfRecipeDetailsInstructions.visibility = View.VISIBLE
                            //binding.card.visibility = View.VISIBLE

                        }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    when (tab?.position) {
                        0 -> {
                            binding.recyclerViewOfRecipeDetailsIngredients.visibility = View.GONE
                             binding.recyclerViewOfRecipeDetailsInstructions.visibility = View.VISIBLE

                        }
                        else -> {
                            binding.recyclerViewOfRecipeDetailsIngredients.visibility = View.VISIBLE
                            binding.recyclerViewOfRecipeDetailsInstructions.visibility = View.GONE


                        }
                    }
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    when (tab?.position) {
                        0 -> {
                            binding.recyclerViewOfRecipeDetailsIngredients.visibility = View.VISIBLE
                            binding.recyclerViewOfRecipeDetailsInstructions.visibility = View.GONE
                        }
                        else -> {
                            binding.recyclerViewOfRecipeDetailsIngredients.visibility = View.GONE
                            binding.recyclerViewOfRecipeDetailsInstructions.visibility =
                                View.VISIBLE
                        }
                    }
                }


            })
            // endregion


        }


    }

    private fun shareIntent(recipe: RecipesDetailsScreenUiState) {
        val shred = Intent().apply {
            this.action = Intent.ACTION_SEND
            srcUrl = recipe.instruction
            this.putExtra(Intent.EXTRA_TEXT, srcUrl)
            this.type = "text/plain"
        }
        startActivity(shred)
    }

    private fun isBookmarkorNull(bookMarked: RecipesItemUiState?) {
        binding.apply {
            if (bookMarked==null) {

                bookMarkEmptyIcon.visibility = View.VISIBLE
                bookMarkFillIcon.visibility = View.GONE


            } else {
                bookMarkEmptyIcon.visibility = View.GONE
                bookMarkFillIcon.visibility = View.VISIBLE


            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}