package com.example.tabkati.ui.recipes


import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tabkati.R
import com.example.tabkati.adapter.RecipeCategoriesAdapter
import com.example.tabkati.adapter.RecipesAdapter
import com.example.tabkati.databinding.FragmentHomeBinding
import com.example.tabkati.ui.login.AuthMainActivity
import com.example.tabkati.utils.State
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var binding: FragmentHomeBinding
    private val homeViwModel by viewModels<HomeViewModel>()
    private val recipesViewModel by viewModels<RecipesViewModel>()
    private val userInfoViewModel by viewModels<UserInfoViewModel>()


    private lateinit var recyclerViewOfRecipeCategories: RecyclerView
    private lateinit var recyclerViewOfRecipes: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater)
        binding = _binding!!
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            // Allows Data Binding to Observe LiveData with the lifecycle of this fragment.
            lifecycleOwner = viewLifecycleOwner
            // @ because inside binding.apply this revers to the binding instance.
            // not the class HomeFragment.
            homeFragment = this@HomeFragment
            recipesViewModel.getRandomRecipes()
            viewModel = recipesViewModel
            userViewmodel = userInfoViewModel

            // recyclerView & adapter of random recipes.
            linkRandomRecipesAdapter()

            searchBar.setOnClickListener {
                goToSearchFragment()
            }
        }
        // get Auth State
        getAuthState()
        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.RESUMED){
                recipesViewModel.respicesUIState.collect {
                    linkRecipesAdapter(it.category)}

            }

        }
    }


    private fun FragmentHomeBinding.linkRandomRecipesAdapter() {
        recyclerViewOfRecipes = recyclerViewOfRandomRecipes
        recyclerViewOfRecipes.layoutManager = LinearLayoutManager(requireContext())
        val recipesAdapter = RecipesAdapter { recipe ->
            // navigate and send the id of the recipe to display the recipe details.
            navigateAndSendRecipesID(recipe)
        }
        recyclerViewOfRecipes.adapter = recipesAdapter

    }

    private fun navigateAndSendRecipesID(recipe: RecipesItemUiState) {
        navigateToRecipesCategory(recipe)
    }

    private fun navigateToRecipesCategory(recipe: RecipesItemUiState) {
        val action = HomeFragmentDirections.actionHomeFragmentToRecipeDetailsFragment(
            recipeId = recipe.id.toString()
        )
        this@HomeFragment.findNavController().navigate(action)
    }

    private fun linkRecipesAdapter(recipeCategoriesData:List<CategoryUIState>) {

        recyclerViewOfRecipeCategories = binding.recyclerViewOfRecipeCate
        recyclerViewOfRecipeCategories.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val recipeCateAdapter = RecipeCategoriesAdapter { recipe ->
            // navigate and send the id of the cat to display the list of recipes by cat.
            navigateAndSendCategory(recipe)
        }
        recyclerViewOfRecipeCategories.adapter = recipeCateAdapter
        recipeCateAdapter.submitList(recipeCategoriesData)
    }

    private fun navigateAndSendCategory(recipe: CategoryUIState) {
        val action = HomeFragmentDirections.actionHomeFragmentToRecipesFragment(
            idOfCat = recipe.id
        )
        this@HomeFragment.findNavController().navigate(action)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_app_bar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.authenticationFragment -> signOut()
            R.id.setting -> showModelSheet()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showModelSheet() {
        val bottomSheet = ModalBottomSheet()
        val fragmentManger = (activity as FragmentActivity).supportFragmentManager
        fragmentManger?.let {
            bottomSheet.show(it, ModalBottomSheet.TAG)
        }

    }

    private fun signOut() {
        homeViwModel.sigOut().observe(viewLifecycleOwner, {
            when (it) {
                is State.Loading -> binding.progressBar.visibility = View.VISIBLE
                is State.Success -> binding.progressBar.visibility = View.GONE
                is State.Failure,
                -> {
                    binding.progressBar.visibility = View.GONE
                }

            }
        })
    }

    // fun to get the user authentication state so no user not authenticated will stay in home fragment.
    private fun getAuthState() {
        homeViwModel.getAuthState().observe(viewLifecycleOwner, {
            // if the user signedOut it = true go to Auth Activity to signIn in.
            if (it) {
                goToAuthActivity()
            }else{
                binding.progressBar.visibility = View.GONE
            }
        })
    }

    private fun goToAuthActivity() {
        val intent = Intent(requireContext(), AuthMainActivity::class.java)
        startActivity(intent)
    }

    //fun to navigate to the SearchFragment.
    private fun goToSearchFragment() {
        val action = HomeFragmentDirections.actionHomeFragmentToSearchFragment()
        this@HomeFragment.findNavController().navigate(action)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}