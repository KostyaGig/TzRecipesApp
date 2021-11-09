package com.zinoview.tzrecipesapp.presentation

import com.zinoview.tzrecipesapp.core.Abstract

interface UiRecipe : Abstract.Recipe {

    class Base(
        private val id: String,
        private val title: String,
        private val imageUrl: String,
        private val description: String,
        private val instruction: String,
        private val difficulty: Int
    ) : UiRecipe{

        override fun <T> map(mapper: Abstract.RecipeMapper<T>): T
            = mapper.map(id, title, imageUrl, description, instruction, difficulty)
    }
}
