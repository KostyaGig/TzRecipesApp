package com.zinoview.tzrecipesapp.service_locator

import com.zinoview.tzrecipesapp.data.DataRecipeMapper
import com.zinoview.tzrecipesapp.data.RecipeRepository
import com.zinoview.tzrecipesapp.data.TextEditor
import com.zinoview.tzrecipesapp.data.cloud.CloudDataSource
import com.zinoview.tzrecipesapp.data.cloud.RecipeApiService
import com.zinoview.tzrecipesapp.domain.DomainRecipeMapper
import com.zinoview.tzrecipesapp.domain.DomainRecipesMapper
import com.zinoview.tzrecipesapp.domain.RecipeInteractor
import com.zinoview.tzrecipesapp.presentation.RecipeViewModel
import com.zinoview.tzrecipesapp.presentation.UiRecipeMapper
import com.zinoview.tzrecipesapp.presentation.UiRecipesMapper
import com.zinoview.tzrecipesapp.presentation.state.UiRecipeStateCommunication
import com.zinoview.tzrecipesapp.presentation.state.UiRecipeStateMapper
import com.zinoview.tzrecipesapp.presentation.state.UiRecipesStateMapper
import com.zinoview.tzrecipesapp.service_locator.core.BaseModule
import com.zinoview.tzrecipesapp.service_locator.core.CoreModule

class RecipeAppModule(
    private val coreModule: CoreModule
) : BaseModule<RecipeViewModel.Base> {

    override fun viewModel(): RecipeViewModel.Base {

        val cloudDataSource = CloudDataSource.Base(coreModule.networkService(RecipeApiService::class.java))
        val repository = RecipeRepository.Base(
            cloudDataSource,
            DataRecipeMapper.Base(),
            coreModule.exceptionMapper
        )

        val interactor = RecipeInteractor.Base(
            repository,
            DomainRecipesMapper.Base(
                DomainRecipeMapper.Base()
            )
        )

        return RecipeViewModel.Base(
            interactor,
            UiRecipesMapper.Base(
                UiRecipeMapper.Base()
            ),
            UiRecipesStateMapper.Base(
                UiRecipeStateMapper.Base(
                    TextEditor.Base()
                )
            ),
            UiRecipeStateCommunication.Base()
        )
    }
}