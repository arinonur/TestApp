package com.example.marvelapp.app.di.module

import com.example.marvelapp.app.source.CharacterDataSource
import com.example.marvelapp.data.network.CharacterService
import com.example.marvelapp.data.network.RequestUtils
import com.example.marvelapp.data.repository.CharacterRepository
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class])
class RepositoryModule {

    @Provides
    fun provideCharacterRepository(
        characterService: CharacterService,
        requestUtils: RequestUtils
    ): CharacterDataSource {
        return CharacterRepository(characterService, requestUtils)
    }

}