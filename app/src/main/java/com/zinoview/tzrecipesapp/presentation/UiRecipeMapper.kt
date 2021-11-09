package com.zinoview.tzrecipesapp.presentation

import com.zinoview.tzrecipesapp.core.Abstract

interface UiRecipeMapper : Abstract.RecipeMapper<UiRecipe> {

    class Base : UiRecipeMapper {
        override fun map(
            id: String,
            title: String,
            imageUrl: String,
            description: String,
            instruction: String,
            difficulty: Int
        ): UiRecipe

             = UiRecipe.Base(id, title, imageUrl, description, instruction, difficulty)
    }
}