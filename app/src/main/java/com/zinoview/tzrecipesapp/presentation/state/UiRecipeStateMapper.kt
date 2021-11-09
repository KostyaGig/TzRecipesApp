package com.zinoview.tzrecipesapp.presentation.state

import com.zinoview.tzrecipesapp.core.Abstract

interface UiRecipeStateMapper : Abstract.RecipeMapper<UiRecipeState> {

    class Base: UiRecipeStateMapper {

        override fun map(
            id: String,
            title: String,
            imageUrl: String,
            description: String,
            instruction: String,
            difficulty: Int
        ): UiRecipeState = UiRecipeState.Base(id,title, imageUrl, description, instruction, difficulty)
    }
}