package com.zinoview.tzrecipesapp.presentation.state

import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.zinoview.tzrecipesapp.core.Abstract
import com.zinoview.tzrecipesapp.presentation.BundleRecipe
import com.zinoview.tzrecipesapp.presentation.core.OnItemClickListener
import com.zinoview.tzrecipesapp.presentation.features.ra01.RecipesAdapter

sealed class UiRecipeState : Abstract.Recipe, Abstract.Recipes {

    override fun <T> map(mapper: Abstract.RecipeMapper<T>): T
        = mapper.map("","","","","",0)

    override fun <T> map(mapper: Abstract.RecipesMapper<T>): T
        = mapper.map("")

    open fun mapView(title: TextView,imageView: ImageView,description: TextView? = null) = Unit

    open fun mapView(error: TextView) = Unit
    open fun onItemClick(onItemClickListener: OnItemClickListener) = Unit

    object Progress : UiRecipeState()

    class Base(
        private val id: String,
        private val title: String,
        private val imageUrl: String,
        private val description: String,
        private val shortDescription: String,
        private val instruction: String,
        private val difficulty: Int
    ) : UiRecipeState() {

        override fun <T> map(mapper: Abstract.RecipeMapper<T>): T
            = mapper.map(id, title, imageUrl, description, instruction, difficulty)

        override fun mapView(titleTv: TextView, imageView: ImageView, descriptionTv: TextView?) {
            titleTv.text = title
            descriptionTv?.text = shortDescription
            Picasso.get().load(imageUrl).into(imageView)
        }

        override fun onItemClick(onItemClickListener: OnItemClickListener) {
            val bundleRecipe = BundleRecipe.Base(id, title, imageUrl, description, instruction, difficulty)
            onItemClickListener.onItemClick(bundleRecipe)
        }
    }

    class Failure(
        private val message: String
    ) : UiRecipeState() {

        override fun mapView(error: TextView) {
            error.text = message
        }
    }
}