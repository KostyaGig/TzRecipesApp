package com.zinoview.tzrecipesapp.presentation.features.ra02

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.zinoview.tzrecipesapp.R
import com.zinoview.tzrecipesapp.presentation.core.BaseFragment

class DetailDetailRecipeFragment : BaseFragment(R.layout.detail_detail_recipe_fragment) {

    private var detailDetailBundleRecipe: DetailDetailBundleRecipe = DetailDetailBundleRecipe.Empty

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val imageRecipeImageView = view.findViewById<ImageView>(R.id.recipe_image)
        val titleRecipeImageView = view.findViewById<TextView>(R.id.recipe_title_tv)

        arguments?.let { bundle ->
            val detailRecipe = bundle.getSerializable(DETAIL_RECIPE_ID_KEY) as DetailDetailBundleRecipe
            detailDetailBundleRecipe = detailRecipe
            detailRecipe.map(imageRecipeImageView,titleRecipeImageView)
        }
    }

    override fun navigateToBack()
        = detailDetailBundleRecipe.navigateToPreviousFragment(navigator, DETAIL_RECIPE_ID_KEY)

}