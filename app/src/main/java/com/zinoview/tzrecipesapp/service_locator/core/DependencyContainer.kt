package com.zinoview.tzrecipesapp.service_locator.core

import com.zinoview.tzrecipesapp.service_locator.RecipeAppModule

interface DependencyContainer {

    fun module(feature: Feature) : BaseModule<*>

    class Base(
        private val coreModule: CoreModule
    ) : DependencyContainer {

        override fun module(feature: Feature): BaseModule<*> = when(feature) {
            is Feature.Recipes -> RecipeAppModule(coreModule)
            else -> throw IllegalArgumentException("DependencyContainer.Base not found arg: ${feature.javaClass}")
        }
    }
}