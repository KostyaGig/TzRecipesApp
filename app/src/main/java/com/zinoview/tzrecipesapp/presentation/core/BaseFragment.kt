package com.zinoview.tzrecipesapp.presentation.core

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.zinoview.tzrecipesapp.core.RecipeApplication
import com.zinoview.tzrecipesapp.presentation.RecipeViewModel

abstract class BaseFragment(@LayoutRes layoutResId: Int) : Fragment(layoutResId) {

    protected val recipeViewModel by lazy {
        ((requireActivity() as MainActivity).application as RecipeApplication).viewModel(RecipeViewModel.Base::class.java,this)
    }

    protected val navigator by lazy {
        (requireActivity() as MainActivity).navigator
    }

    protected companion object {
        const val DETAIL_RECIPE_ID_KEY = "id_det"
    }

    abstract fun navigateToBack()
}