package com.zinoview.tzrecipesapp.presentation.core.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zinoview.tzrecipesapp.R
import com.zinoview.tzrecipesapp.presentation.core.BaseFragment

interface Navigator {

    fun navigateTo(fragment: BaseFragment)

    class Empty : Navigator {

        override fun navigateTo(fragment: BaseFragment) = Unit
    }

    class Base(
        private val activity: AppCompatActivity
    ) : Navigator {

        override fun navigateTo(fragment: BaseFragment) {
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container,fragment)
                .commit()
        }
    }
}