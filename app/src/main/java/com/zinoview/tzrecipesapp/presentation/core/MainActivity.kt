package com.zinoview.tzrecipesapp.presentation.core

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.zinoview.tzrecipesapp.R
import com.zinoview.tzrecipesapp.presentation.core.navigation.Exit
import com.zinoview.tzrecipesapp.presentation.core.navigation.Navigator
import com.zinoview.tzrecipesapp.presentation.features.ra01.RecipesFragment

//todo remove later
fun Any?.log(text: String) {
    Log.d("zinoviewk",text)
}

class MainActivity : AppCompatActivity(), Exit {

    var navigator: Navigator = Navigator.Empty()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigator = Navigator.Base(this)
        navigator.navigateTo(RecipesFragment())
    }

    override fun exit() = finish()

    override fun onBackPressed() {
        val baseFragment = supportFragmentManager.fragments[0] as BaseFragment
        baseFragment.navigateToBack()
    }
}