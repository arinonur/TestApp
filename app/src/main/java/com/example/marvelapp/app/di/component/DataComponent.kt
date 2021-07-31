package com.example.marvelapp.app.di.component

import com.example.marvelapp.app.di.module.RepositoryModule
import com.example.marvelapp.app.source.CharacterDataSource
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class])
interface DataComponent {

    fun provideCharacterRepository(): CharacterDataSource

}