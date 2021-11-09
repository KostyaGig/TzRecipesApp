package com.zinoview.tzrecipesapp.data.cloud

interface CloudDataSource {

    suspend fun recipes() : List<CloudRecipe>

    class Base(
        private val recipeApiService: RecipeApiService
    ) : CloudDataSource {

        override suspend fun recipes(): List<CloudRecipe>
            =  recipeApiService.recipes().models()
    }

    class Test : CloudDataSource {

        override suspend fun recipes(): List<CloudRecipe> {
            return listOf(
                CloudRecipe.TestRecipe("1","Pizza"),
                CloudRecipe.TestRecipe("2","Burger")
            )
        }

    }
}