package com.zinoview.tzrecipesapp.data.cloud

import retrofit2.http.GET

interface RecipeApiService {

    @GET("/recipes")
    suspend fun recipes() : CloudRecipes.Base
}