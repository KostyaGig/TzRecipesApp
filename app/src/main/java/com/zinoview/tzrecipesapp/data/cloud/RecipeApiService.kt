package com.zinoview.tzrecipesapp.data.cloud

import retrofit2.http.GET
import retrofit2.http.Path

interface RecipeApiService {

    @GET("/recipes")
    suspend fun recipes() : CloudRecipes.Base

    @GET("/recipes/{id}")
    suspend fun recipe(@Path("id") id: String) : SimilarRecipes.Base
}