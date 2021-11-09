package com.zinoview.tzrecipesapp.service_locator.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zinoview.tzrecipesapp.presentation.RecipeViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(
    private val dependencyContainer: DependencyContainer
) : ViewModelProvider.Factory {

    private val featuresByClass = HashMap<Class<*>,Feature>().apply {
        put(RecipeViewModel.Base::class.java,Feature.Recipes)
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val feature = featuresByClass[modelClass] ?: throw IllegalArgumentException("Feature by class ${modelClass.javaClass} not found")
        return dependencyContainer.module(feature).viewModel() as T
    }

}