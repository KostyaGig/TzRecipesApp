package com.zinoview.tzrecipesapp.presentation.features.ra02

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.zinoview.tzrecipesapp.core.Abstract
import com.zinoview.tzrecipesapp.presentation.BundleRecipe
import com.zinoview.tzrecipesapp.presentation.RecipeViewModel
import com.zinoview.tzrecipesapp.presentation.core.navigation.Navigator
import java.io.Serializable

interface DetailDetailBundleRecipe : Serializable, Abstract.Recipe {

    object Empty : DetailDetailBundleRecipe

    override fun <T> map(mapper: Abstract.RecipeMapper<T>): T
        = mapper.map("","","","","",0)

    fun map(
        recipeImage: ImageView,
        titleTextView: TextView
    ) = Unit

    fun similarRecipes(recipeViewModel: RecipeViewModel)
        = Unit

    fun navigateToPreviousFragment(navigator: Navigator,key: String)
        = Unit

    class Base(
        private val previousBundleRecipe: BundleRecipe,
        private val id: String,
        private val title: String,
        private val imageUrl: String,
    ) : DetailDetailBundleRecipe {

        override fun map(
            recipeImage: ImageView,
            titleTextView: TextView,
        ) {
            Picasso.get().load(imageUrl).into(recipeImage)
            titleTextView.text = title
        }

        override fun <T> map(mapper: Abstract.RecipeMapper<T>): T =
            mapper.map(id, title, imageUrl,"","",0)

        override fun similarRecipes(recipeViewModel: RecipeViewModel) =
            recipeViewModel.similarRecipes(id)

        override fun navigateToPreviousFragment(navigator: Navigator,key: String) {
            val detailRecipeFragment = DetailRecipeFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(key,previousBundleRecipe)
                }
            }
            navigator.navigateTo(detailRecipeFragment)
        }

    }
}