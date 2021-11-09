package com.zinoview.tzrecipesapp.data

import com.zinoview.tzrecipesapp.core.Abstract

interface DataRecipesMapper : Abstract.RecipesMapper<DataRecipes> {

    class Base(
        private val dataRecipeMapper: DataRecipeMapper
    ) : DataRecipesMapper {

        override fun map(message: String): DataRecipes
            = DataRecipes.Failure(message)

        override fun map(recipes: List<Abstract.Recipe>): DataRecipes
            = DataRecipes.Success(recipes.map { recipe ->
                recipe.map(dataRecipeMapper)
        })
    }
}