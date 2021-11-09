package com.zinoview.tzrecipesapp.presentation.state

import com.zinoview.tzrecipesapp.core.Abstract
import com.zinoview.tzrecipesapp.data.TextEditor

interface UiRecipeStateMapper : Abstract.RecipeMapper<UiRecipeState> {

    class Base(
        private val textEditor: TextEditor
    ): UiRecipeStateMapper {

        override fun map(
            id: String,
            title: String,
            imageUrl: String,
            description: String,
            instruction: String,
            difficulty: Int
        ): UiRecipeState = UiRecipeState.Base(id,title, imageUrl, description, textEditor.substring(description) ,instruction, difficulty)
    }
}