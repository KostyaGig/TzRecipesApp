package com.zinoview.tzrecipesapp.data.cloud

import com.google.gson.annotations.SerializedName
import com.zinoview.tzrecipesapp.core.Abstract
import com.zinoview.tzrecipesapp.presentation.core.log
import kotlin.math.log

interface CloudRecipe : Abstract.Recipe {

    class Base(
        @SerializedName("uuid")
        private val id: String = "",
        @SerializedName("name")
        val title: String = "",
        @SerializedName("images")
        private val imageUrl: List<String> = emptyList(),
        @SerializedName("description")
        private val description: String = "",
        @SerializedName("instructions")
        private val instruction: String = "",
        @SerializedName("difficulty")
        private val difficulty: Int = 0
    ) : CloudRecipe {

        override fun <T> map(mapper: Abstract.RecipeMapper<T>): T
            = mapper.map(id, title, imageUrl.first(), description, instruction, difficulty)
    }

    data class TestRecipe(
        private val id: String,
        private val title: String
    ) : CloudRecipe {

        override fun <T> map(mapper: Abstract.RecipeMapper<T>): T
            = mapper.map("","","","","",0)
    }
}

interface CloudRecipes {

    fun models() : List<CloudRecipe.Base>

    class Base(
        @SerializedName("recipes")
        private val cloudRecipes: List<CloudRecipe.Base>
    ) : CloudRecipes {

        override fun models(): List<CloudRecipe.Base>
            = cloudRecipes
    }
}