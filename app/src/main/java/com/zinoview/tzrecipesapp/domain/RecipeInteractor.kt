package com.zinoview.tzrecipesapp.domain

import com.zinoview.tzrecipesapp.data.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface RecipeInteractor {

    suspend fun recipes() : Flow<DomainRecipes>

    suspend fun similarRecipes(id: String) : Flow<DomainRecipes>

    class Base (
        private val repository: RecipeRepository,
        private val domainRecipesMapper: DomainRecipesMapper
    ) : RecipeInteractor {

        override suspend fun recipes(): Flow<DomainRecipes> {
            return repository.recipes().map { dataRecipes ->
                dataRecipes.map(domainRecipesMapper)
            }
        }

        override suspend fun similarRecipes(id: String): Flow<DomainRecipes> {
            return repository.similarRecipes(id).map { dataRecipes ->
                dataRecipes.map(domainRecipesMapper)
            }
        }

    }
}