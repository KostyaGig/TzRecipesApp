package com.zinoview.tzrecipesapp.data

import com.zinoview.tzrecipesapp.core.Abstract
import com.zinoview.tzrecipesapp.data.cloud.CloudDataSource
import com.zinoview.tzrecipesapp.data.cloud.CloudRecipe
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Test for [com.zinoview.tzrecipesapp.data.RecipeRepository]
 * */

class RecipeRepositoryTest {

    private var repository: RecipeRepository? = null

    @Before
    fun setUp() {

        val testCloudDataSource = CloudDataSource.Test()
        repository = RecipeRepository.Test(testCloudDataSource)
    }

    @Test
    fun test_success_fetching_data() = runBlocking {
        val expected = flow { emit(
            DataRecipes.Test(
                listOf(
                    CloudRecipe.TestRecipe("1","Pizza"),
                    CloudRecipe.TestRecipe("2","Burger")
                )
            )
        )}.first()

        repository?.recipes()
        val actual = repository?.recipes()?.first()
        assertEquals(expected, actual)
    }

    @Test
    fun test_failure_fetching_data() = runBlocking {
        val expected = flow { emit(
            DataRecipes.Failure("No connection")
        )}.first()

        val actual = repository?.recipes()?.first()

        assertEquals(expected,actual)
    }
}