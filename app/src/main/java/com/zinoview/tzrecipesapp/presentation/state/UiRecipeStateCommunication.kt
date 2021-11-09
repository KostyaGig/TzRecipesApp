package com.zinoview.tzrecipesapp.presentation.state

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.zinoview.tzrecipesapp.presentation.core.Observe

interface UiRecipeStateCommunication : Observe<List<UiRecipeState>> {

    fun postValue(value: List<UiRecipeState>)

    class Base : UiRecipeStateCommunication {

        private val uiRecipeStateLiveData = MutableLiveData<List<UiRecipeState>>()

        override fun postValue(value: List<UiRecipeState>) {
            uiRecipeStateLiveData.value = value
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<List<UiRecipeState>>) {
            uiRecipeStateLiveData.observe(owner, observer)
        }


    }
}