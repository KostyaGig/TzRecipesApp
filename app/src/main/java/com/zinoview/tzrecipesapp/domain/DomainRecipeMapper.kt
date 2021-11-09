package com.zinoview.tzrecipesapp.domain

import com.zinoview.tzrecipesapp.core.Abstract

interface DomainRecipeMapper : Abstract.RecipeMapper<DomainRecipe> {

    class Base : DomainRecipeMapper {
        override fun map(
            id: String,
            title: String,
            imageUrl: String,
            description: String,
            instruction: String,
            difficulty: Int
        ): DomainRecipe
             = DomainRecipe.Base(id, title, imageUrl, description, instruction, difficulty)
    }
}