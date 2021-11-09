package com.zinoview.tzrecipesapp.data

import com.zinoview.tzrecipesapp.core.Abstract

interface DataRecipeMapper : Abstract.RecipeMapper<DataRecipe> {

    class Base : DataRecipeMapper {
        override fun map(
            id: String,
            title: String,
            imageUrl: String,
            description: String,
            instruction: String,
            difficulty: Int
        ): DataRecipe
             = DataRecipe.Base(id, title, imageUrl, description, instruction, difficulty)
    }
}