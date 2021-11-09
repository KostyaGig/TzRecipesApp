package com.zinoview.tzrecipesapp.data

import com.zinoview.tzrecipesapp.core.Abstract
import com.zinoview.tzrecipesapp.data.cloud.CloudRecipe

sealed class DataRecipes : Abstract.Recipes {

    class Success(
        private val recipes: List<DataRecipe>
    ) : DataRecipes() {

        override fun <T> map(mapper: Abstract.RecipesMapper<T>): T
            = mapper.map(recipes)
    }

    data class Failure(
        private val message: String
    ) : DataRecipes() {

        override fun <T> map(mapper: Abstract.RecipesMapper<T>): T
            = mapper.map(message)
    }

    data class Test(
        val recipes: List<CloudRecipe>
    ) : DataRecipes() {

        override fun <T> map(mapper: Abstract.RecipesMapper<T>): T
            = mapper.map("")
    }
}