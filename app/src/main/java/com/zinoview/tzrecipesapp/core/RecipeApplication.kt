package com.zinoview.tzrecipesapp.core

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

import com.zinoview.tzrecipesapp.service_locator.core.CoreModule
import com.zinoview.tzrecipesapp.service_locator.core.DependencyContainer
import com.zinoview.tzrecipesapp.service_locator.core.ViewModelFactory


class RecipeApplication : Application() {

    private val coreModule = CoreModule()

    private val viewModelFactory by lazy {
        ViewModelFactory(DependencyContainer.Base(
            coreModule
        ))
    }

    override fun onCreate() {
        super.onCreate()

        coreModule.init(this)
    }

    fun <T : ViewModel> viewModel(clazz: Class<T>,owner: ViewModelStoreOwner)
        = ViewModelProvider(owner,viewModelFactory).get(clazz)
}