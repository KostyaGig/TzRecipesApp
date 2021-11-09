package com.zinoview.tzrecipesapp.data

import com.zinoview.tzrecipesapp.core.Abstract

interface DataRecipe : Abstract.Recipe {

    class Base(
        private val id: String,
        private val title: String,
        private val imageUrl: String,
        private val description: String,
        private val instruction: String,
        private val difficulty: Int
    ) : DataRecipe{

        override fun <T> map(mapper: Abstract.RecipeMapper<T>): T
            = mapper.map(id, title, imageUrl, description, instruction, difficulty)
    }
}
