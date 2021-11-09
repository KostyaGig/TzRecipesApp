package com.zinoview.tzrecipesapp.domain

import com.zinoview.tzrecipesapp.core.Abstract

sealed class DomainRecipes : Abstract.Recipes {

    class Success(
        private val recipes: List<DomainRecipe>
    ) : DomainRecipes() {

        override fun <T> map(mapper: Abstract.RecipesMapper<T>): T
            = mapper.map(recipes)
    }

    class Failure(
        private val message: String
    ) : DomainRecipes() {

        override fun <T> map(mapper: Abstract.RecipesMapper<T>): T
            = mapper.map(message)
    }
}