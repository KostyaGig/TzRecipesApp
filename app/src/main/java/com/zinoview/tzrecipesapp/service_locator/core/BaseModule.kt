package com.zinoview.tzrecipesapp.service_locator.core

import androidx.lifecycle.ViewModel

interface BaseModule<T : ViewModel> {

    fun viewModel() : T
}