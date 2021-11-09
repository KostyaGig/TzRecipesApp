package com.zinoview.tzrecipesapp.presentation

import com.zinoview.tzrecipesapp.core.Abstract

sealed class UiRecipes : Abstract.Recipes {

    class Success(
        private val recipes: List<UiRecipe>
    ) : UiRecipes() {

        override fun <T> map(mapper: Abstract.RecipesMapper<T>): T
            = mapper.map(recipes)
    }

    class Failure(
        private val message: String
    ) : UiRecipes() {

        override fun <T> map(mapper: Abstract.RecipesMapper<T>): T
            = mapper.map(message)
    }
}