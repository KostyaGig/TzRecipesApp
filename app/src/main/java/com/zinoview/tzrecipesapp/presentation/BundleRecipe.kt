package com.zinoview.tzrecipesapp.presentation

import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.zinoview.tzrecipesapp.core.Abstract
import com.zinoview.tzrecipesapp.presentation.features.ra02.DetailDetailBundleRecipe
import java.io.Serializable

interface BundleRecipe : Serializable, Abstract.Recipe {

    object Empty : BundleRecipe

    fun map(recipeImage: ImageView,titleTextView: TextView,descriptionTextView: TextView,difficultTextView: TextView)
        = Unit

    fun similarRecipes(recipeViewModel: RecipeViewModel)
        = Unit

    override fun <T> map(mapper: Abstract.RecipeMapper<T>): T
        = mapper.map("","","","","",0)

    fun fetchId(): String = ""

    fun mapToDetailDetailBundle(previousBundleRecipe: BundleRecipe) : DetailDetailBundleRecipe
        = DetailDetailBundleRecipe.Empty

    class Base(
        private val id: String,
        private val title: String,
        private val imageUrl: String,
        private val description: String,
        private val instruction: String,
        private val difficulty: Int
    ) : BundleRecipe {

        override fun map(
            recipeImage: ImageView,
            titleTextView: TextView,
            descriptionTextView: TextView,
            difficultTextView: TextView
        ) {
            Picasso.get().load(imageUrl).into(recipeImage)
            titleTextView.text = title
            descriptionTextView.text = description
            difficultTextView.text = "Difficulty: $difficulty"
        }

        override fun <T> map(mapper: Abstract.RecipeMapper<T>): T
            = mapper.map(id, title, imageUrl, description, instruction, difficulty)

        override fun similarRecipes(recipeViewModel: RecipeViewModel)
            = recipeViewModel.similarRecipes(id)

        override fun fetchId(): String
            = id

        override fun mapToDetailDetailBundle(
            previousBundleRecipe: BundleRecipe
        ): DetailDetailBundleRecipe
            = DetailDetailBundleRecipe.Base(
                previousBundleRecipe,id, title, imageUrl
            )

    }
}