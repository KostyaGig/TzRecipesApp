package com.zinoview.tzrecipesapp.core

interface Abstract {

    /**
     * Test for Recipe [com.zinoview.tzrecipesapp.data.TestRecipeMapper]
     * */
    interface Recipe {

        fun <T> map(mapper: RecipeMapper<T>) : T
    }

    interface RecipeMapper<T> : Mapper {

        fun map(id: String,title: String,imageUrl: String,description: String,instruction: String,difficulty: Int) : T
    }

    interface RecipesMapper<T> {
        fun map(recipes: List<Recipe>) : T

        fun map(message: String) : T
    }


    interface Recipes {

        fun <T> map(mapper: RecipesMapper<T>) : T
    }

    interface Mapper

}