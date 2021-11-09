package com.zinoview.tzrecipesapp.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.zinoview.tzrecipesapp.R
import com.zinoview.tzrecipesapp.core.RecipeApplication
import com.zinoview.tzrecipesapp.presentation.RecipesAdapter
import com.zinoview.tzrecipesapp.presentation.core.BaseFragment
import com.zinoview.tzrecipesapp.presentation.core.MainActivity
import com.zinoview.tzrecipesapp.presentation.core.navigation.Exit

class RecipesFragment : BaseFragment(R.layout.recipes_fragment) {

    private val recipeViewModel by lazy {
        ((requireActivity() as MainActivity).application as RecipeApplication).recipeViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val recyclerView = view.findViewById<RecyclerView>(R.id.recipes_rec_view)
        val adapter = RecipesAdapter()
        recyclerView.adapter = adapter

        recipeViewModel.observe(this) { uiRecipes ->
            adapter.update(uiRecipes)
        }

        recipeViewModel.recipes()
    }

    override fun navigateToBack() = (requireActivity() as Exit).exit()
}