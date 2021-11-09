package com.zinoview.tzrecipesapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zinoview.tzrecipesapp.domain.RecipeInteractor
import com.zinoview.tzrecipesapp.presentation.core.Observe
import com.zinoview.tzrecipesapp.presentation.core.log
import com.zinoview.tzrecipesapp.presentation.state.UiRecipeState
import com.zinoview.tzrecipesapp.presentation.state.UiRecipeStateCommunication
import com.zinoview.tzrecipesapp.presentation.state.UiRecipesStateMapper
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map

interface RecipeViewModel : Observe<List<UiRecipeState>> {

    fun recipes()

    class Base(
        private val interactor: RecipeInteractor,
        private val uiRecipeMapper: UiRecipesMapper,
        private val uiRecipesStateMapper: UiRecipesStateMapper,
        private val recipeCommunication: UiRecipeStateCommunication,
        private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
    ) : RecipeViewModel, ViewModel() {

        override fun recipes() {
            recipeCommunication.postValue(listOf(UiRecipeState.Progress))
            viewModelScope.launch(defaultDispatcher) {
                val domainRecipes = interactor.recipes()
                val uiRecipes = domainRecipes.map { domain -> domain.map(uiRecipeMapper) }
                val uiStateRecipes = uiRecipes.map { ui -> ui.map(uiRecipesStateMapper) }

                withContext(Dispatchers.Main) {
                    uiStateRecipes.collect { uiRecipesState ->
                        recipeCommunication.postValue(uiRecipesState)
                    }
                }
            }
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<List<UiRecipeState>>)
            = recipeCommunication.observe(owner,observer)

    }
}