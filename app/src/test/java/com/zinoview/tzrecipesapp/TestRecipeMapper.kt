package com.zinoview.tzrecipesapp

import com.zinoview.tzrecipesapp.data.DataRecipe
import com.zinoview.tzrecipesapp.domain.DomainRecipe
import com.zinoview.tzrecipesapp.domain.DomainRecipeMapper
import org.junit.Assert.*
import org.junit.Test

/**
 * Test for [com.zinoview.tzrecipesapp.data.DataRecipe.Base]
 */

class TestRecipeMapper {

    @Test
    fun test_data_to_domain_map() {
        val dataRecipe = DataRecipe.Base("1", "Pizza", "", "", "", 0)

        val expected = DomainRecipe.Base("1","Pizza","","","",0)
        val actual = dataRecipe.map(DomainRecipeMapper.Base())

        assertEquals(expected, actual)
    }
}