package com.zinoview.tzrecipesapp.presentation.state

import com.zinoview.tzrecipesapp.core.Abstract

interface UiRecipesStateMapper : Abstract.RecipesMapper<List<UiRecipeState>> {

    class Base(
        private val uiRecipeStateMapper: UiRecipeStateMapper
    ) : UiRecipesStateMapper {

        override fun map(recipes: List<Abstract.Recipe>): List<UiRecipeState>
            = recipes.map { recipe -> recipe.map(uiRecipeStateMapper) }

        override fun map(message: String): List<UiRecipeState>
            = listOf(UiRecipeState.Failure(message))
    }
}