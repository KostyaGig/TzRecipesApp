package com.zinoview.tzrecipesapp.presentation.core

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment(@LayoutRes layoutResId: Int) : Fragment(layoutResId) {

    protected val navigator by lazy {
        (requireActivity() as MainActivity).navigator
    }

    protected companion object {
        const val DETAIL_RECIPE_ID_KEY = "id_det"
    }

    abstract fun navigateToBack()
}