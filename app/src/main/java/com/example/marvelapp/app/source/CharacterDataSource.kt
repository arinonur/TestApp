package com.example.marvelapp.app.source

import com.example.marvelapp.data.model.character.Character
import com.example.marvelapp.data.model.character.CharacterComics
import com.example.marvelapp.data.model.character.CharacterInfo
import io.reactivex.Single

interface CharacterDataSource {

    fun getCharacters(limit: Int, offset: Int): Single<List<Character>>

    fun getCharacterInfo(id: Int): Single<CharacterInfo>

    fun getCharacterComics(id: Int): Single<List<CharacterComics>>

}