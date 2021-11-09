package com.zinoview.tzrecipesapp.domain

import com.zinoview.tzrecipesapp.core.Abstract

interface DomainRecipesMapper : Abstract.RecipesMapper<DomainRecipes> {

    class Base(
        private val domainRecipeMapper: DomainRecipeMapper
    ) : DomainRecipesMapper {

        override fun map(message: String): DomainRecipes
            = DomainRecipes.Failure(message)

        override fun map(recipes: List<Abstract.Recipe>): DomainRecipes
            = DomainRecipes.Success(recipes.map { recipe ->
                recipe.map(domainRecipeMapper)
        })
    }
}