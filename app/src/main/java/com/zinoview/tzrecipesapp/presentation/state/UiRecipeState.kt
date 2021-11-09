package com.zinoview.tzrecipesapp.presentation.state

import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.zinoview.tzrecipesapp.core.Abstract

sealed class UiRecipeState : Abstract.Recipe, Abstract.Recipes {

    override fun <T> map(mapper: Abstract.RecipeMapper<T>): T
        = mapper.map("","","","","",0)

    override fun <T> map(mapper: Abstract.RecipesMapper<T>): T
        = mapper.map("")

    open fun mapView(title: TextView,imageView: ImageView,description: TextView) = Unit

    open fun mapView(error: TextView) = Unit

    object Progress : UiRecipeState()

    class Base(
        private val id: String,
        private val title: String,
        private val imageUrl: String,
        private val description: String,
        private val instruction: String,
        private val difficulty: Int
    ) : UiRecipeState() {

        override fun <T> map(mapper: Abstract.RecipeMapper<T>): T
            = mapper.map(id, title, imageUrl, description, instruction, difficulty)

        override fun mapView(titleTv: TextView, imageView: ImageView, descriptionTv: TextView) {
            titleTv.text = title
            descriptionTv.text = description
            Picasso.get().load(imageUrl).into(imageView)
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