package com.zinoview.tzrecipesapp.core

import android.app.Application
import com.zinoview.tzrecipesapp.data.DataRecipeMapper
import com.zinoview.tzrecipesapp.data.ExceptionMapper
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
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecipeApplication : Application() {

    lateinit var recipeViewModel: RecipeViewModel

    private companion object {
        private const val BASE_URL = "https://test.kode-t.ru"
    }

    override fun onCreate() {
        super.onCreate()

        val client =
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val cloudDataSource = CloudDataSource.Base(retrofit.create(RecipeApiService::class.java))
        val repository = RecipeRepository.Base(
            cloudDataSource,
            DataRecipeMapper.Base(),
            ExceptionMapper.Base(ResourceProvider.Base(this))
        )

        val interactor = RecipeInteractor.Base(
            repository,
            DomainRecipesMapper.Base(
                DomainRecipeMapper.Base()
            )
        )

        recipeViewModel = RecipeViewModel.Base(
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