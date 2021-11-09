package com.zinoview.tzrecipesapp.data

import com.zinoview.tzrecipesapp.data.cloud.CloudDataSource
import com.zinoview.tzrecipesapp.presentation.core.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Test for repository [com.zinoview.tzrecipesapp.data.RecipeRepositoryTest]
 * */
interface RecipeRepository {

    suspend fun recipes() : Flow<DataRecipes>

    class Base(
        private val cloudDataSource: CloudDataSource,
        private val dataRecipeMapper: DataRecipeMapper,
        private val exceptionMapper: ExceptionMapper
    ) : RecipeRepository {

        override suspend fun recipes(): Flow<DataRecipes> {
            delay(DELAY.toLong())
            return try {
                val cloudRecipes = cloudDataSource.recipes()
                val dataRecipes = cloudRecipes.map { cloudRecipe -> cloudRecipe.map(dataRecipeMapper)  }
                flow { emit(DataRecipes.Success(dataRecipes)) }
            } catch (e: Exception) {
                val errorMessage = exceptionMapper.map(e)
                flow { emit(DataRecipes.Failure(errorMessage)) }
            }
        }

        private companion object {
            private const val DELAY = 2500
        }
    }

    class Test(
        private val cloudDataSource: CloudDataSource
    ) : RecipeRepository {

        private var count = 0

        override suspend fun recipes(): Flow<DataRecipes> {
            val result = if (count % 2 == 0) {
                flow { emit(DataRecipes.Failure("No connection")) }
            } else {
                val cloudRecipes = cloudDataSource.recipes()
                flow { emit(DataRecipes.Test(cloudRecipes)) }
            }
            count++
            return result
        }

    }
}