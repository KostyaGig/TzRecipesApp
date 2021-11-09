package com.zinoview.tzrecipesapp.data.cloud


interface CloudDataSource {

    suspend fun recipes() : List<CloudRecipe>

    suspend fun similarRecipes(id: String) : List<CloudRecipe>

    class Base(
        private val recipeApiService: RecipeApiService
    ) : CloudDataSource {

        override suspend fun recipes(): List<CloudRecipe>
            =  recipeApiService.recipes().models()

        override suspend fun similarRecipes(id: String): List<CloudRecipe> {
            return recipeApiService.recipe(id).toCloudRecipe()
        }
    }

    class Test : CloudDataSource {

        override suspend fun recipes(): List<CloudRecipe> {
            return listOf(
                CloudRecipe.TestRecipe("1","Pizza"),
                CloudRecipe.TestRecipe("2","Burger")
            )
        }

        override suspend fun similarRecipes(id: String): List<CloudRecipe.Base>
            = emptyList()

    }
}