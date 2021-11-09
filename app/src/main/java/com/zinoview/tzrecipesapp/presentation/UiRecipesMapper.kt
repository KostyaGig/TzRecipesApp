package com.zinoview.tzrecipesapp.presentation

import com.zinoview.tzrecipesapp.core.Abstract

interface UiRecipesMapper : Abstract.RecipesMapper<UiRecipes> {

    class Base(
        private val uiRecipeMapper: UiRecipeMapper
    ) : UiRecipesMapper {

        override fun map(message: String): UiRecipes
            = UiRecipes.Failure(message)

        override fun map(recipes: List<Abstract.Recipe>): UiRecipes
            = UiRecipes.Success(recipes.map { recipe ->
                recipe.map(uiRecipeMapper)
        })
    }
}