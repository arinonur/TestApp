package com.example.marvelapp.app.ui.main

import android.os.Bundle
import com.example.marvelapp.R
import com.example.marvelapp.app.base.ui.BaseActivity
import com.example.marvelapp.app.navigation.MainNavigator
import com.example.marvelapp.app.navigation.MainNavigatorProvider
import com.example.marvelapp.app.ui.characters.CharactersFragment

class MainActivity : BaseActivity(), MainNavigatorProvider {

    private lateinit var mainNavigator: MainNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainNavigator = MainNavigator(supportFragmentManager)

        if (savedInstanceState == null) {
            val charactersFragment = CharactersFragment.newInstance()
            mainNavigator.addFragment(
                charactersFragment,
                R.id.flMainContainer,
                false,
                CharactersFragment.TAG
            )
        }

    }

    override fun provideMainNavigator(): MainNavigator {
        return mainNavigator
    }

}
