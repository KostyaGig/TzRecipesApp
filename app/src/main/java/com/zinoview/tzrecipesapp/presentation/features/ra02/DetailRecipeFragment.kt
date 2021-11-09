package com.zinoview.tzrecipesapp.presentation.features.ra02

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zinoview.tzrecipesapp.R
import com.zinoview.tzrecipesapp.core.RecipeApplication
import com.zinoview.tzrecipesapp.presentation.BundleRecipe
import com.zinoview.tzrecipesapp.presentation.core.BaseFragment
import com.zinoview.tzrecipesapp.presentation.core.MainActivity
import com.zinoview.tzrecipesapp.presentation.core.OnItemClickListener
import com.zinoview.tzrecipesapp.presentation.features.ra01.RecipesFragment

class DetailRecipeFragment : BaseFragment(R.layout.detail_recipe_fragment) {

    private val recipeViewModel by lazy {
        ((requireActivity() as MainActivity).application as RecipeApplication).recipeViewModel
    }

    private var detailRecipeBundle:BundleRecipe = BundleRecipe.Empty

    override fun navigateToBack()
        = navigator.navigateTo(RecipesFragment())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val imageRecipeImageView = view.findViewById<ImageView>(R.id.recipe_image)
        val titleRecipeImageView = view.findViewById<TextView>(R.id.recipe_title_tv)
        val descriptionRecipeTextView = view.findViewById<TextView>(R.id.recipe_description_tv)
        val difficultyRecipeTextView = view.findViewById<TextView>(R.id.difficulty_tv)

        val similarRecipesRecyclerView = view.findViewById<RecyclerView>(R.id.similar_recipes_rec_view)
        similarRecipesRecyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)

        val adapter = DetailRecipeAdapter(object : OnItemClickListener {
            override fun onItemClick(bundleRecipe: BundleRecipe) {
                val detailDetailBundleRecipe = bundleRecipe.mapToDetailDetailBundle(detailRecipeBundle)
                val args = Bundle().apply {
                    putSerializable(DETAIL_RECIPE_ID_KEY,detailDetailBundleRecipe)
                }
                val fragment = DetailDetailRecipeFragment().apply {
                    arguments = args
                }
                navigator.navigateTo(fragment)
            }
        })
        similarRecipesRecyclerView.adapter = adapter

        recipeViewModel.observe(this) { uiRecipes ->
            adapter.update(uiRecipes)
        }

        arguments?.let { bundle ->
            val detailRecipe = bundle.getSerializable(DETAIL_RECIPE_ID_KEY) as BundleRecipe
            detailRecipeBundle = detailRecipe
            detailRecipe.similarRecipes(recipeViewModel)
            detailRecipe.map(imageRecipeImageView,titleRecipeImageView,descriptionRecipeTextView,difficultyRecipeTextView)
        }

    }
}