package com.zinoview.tzrecipesapp.presentation.features.ra01

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.zinoview.tzrecipesapp.R
import com.zinoview.tzrecipesapp.presentation.BundleRecipe
import com.zinoview.tzrecipesapp.presentation.core.BaseFragment
import com.zinoview.tzrecipesapp.presentation.core.OnItemClickListener
import com.zinoview.tzrecipesapp.presentation.core.navigation.Exit
import com.zinoview.tzrecipesapp.presentation.features.ra02.DetailRecipeFragment

class RecipesFragment : BaseFragment(R.layout.recipes_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val recyclerView = view.findViewById<RecyclerView>(R.id.recipes_rec_view)
        val adapter = RecipesAdapter(object : OnItemClickListener {
            override fun onItemClick(bundleRecipe: BundleRecipe) {
                val args = Bundle().apply {
                    putSerializable(DETAIL_RECIPE_ID_KEY,bundleRecipe)
                }
                val fragment = DetailRecipeFragment().apply {
                    arguments = args
                }
                navigator.navigateTo(fragment)
            }
        })
        recyclerView.adapter = adapter

        recipeViewModel.observe(this) { uiRecipes ->
            adapter.update(uiRecipes)
        }

        recipeViewModel.recipes()
    }

    override fun navigateToBack() = (requireActivity() as Exit).exit()
}