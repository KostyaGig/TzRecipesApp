package com.zinoview.tzrecipesapp.presentation.core

import com.zinoview.tzrecipesapp.presentation.BundleRecipe

interface OnItemClickListener {

    object Empty : OnItemClickListener {
        override fun onItemClick(bundleRecipe: BundleRecipe) = Unit
    }

    fun onItemClick(bundleRecipe: BundleRecipe)
}