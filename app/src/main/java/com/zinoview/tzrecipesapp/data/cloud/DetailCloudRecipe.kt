package com.zinoview.tzrecipesapp.data.cloud

import com.google.gson.annotations.SerializedName
import com.zinoview.tzrecipesapp.presentation.core.log

interface SimilarRecipes {

    fun toCloudRecipe(): List<CloudRecipe>

    class Base(
        @SerializedName("recipe")
        private val recipe: SimilarRecipe.Base
    ) : SimilarRecipes {

        override fun toCloudRecipe(): List<CloudRecipe> {
            return recipe.fetchSimilarRecipes()
        }
    }
}

interface SimilarRecipe {

    fun fetchSimilarRecipes() : List<CloudRecipe>

    class Base (
        @SerializedName("similar")
        private val similar: List<CloudSimilarRecipe.Base>
    ) : SimilarRecipe {

        override fun fetchSimilarRecipes(): List<CloudRecipe> {
            return similar.map { it.toCloudRecipe() }
        }
    }
}

interface CloudSimilarRecipe {

    fun toCloudRecipe() : CloudRecipe

    class Base(
        @SerializedName("uuid")
        private val id: String = "",
        @SerializedName("name")
        private val title: String = "",
        @SerializedName("image")
        private val imageUrl: String = ""
    ) : CloudSimilarRecipe {

        override fun toCloudRecipe() : CloudRecipe
            = CloudRecipe.Base(id,title, listOf(imageUrl))
    }
}


