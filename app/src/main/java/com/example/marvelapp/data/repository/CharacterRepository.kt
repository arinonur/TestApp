package com.example.marvelapp.data.repository

import com.example.marvelapp.app.source.CharacterDataSource
import com.example.marvelapp.data.model.character.Character
import com.example.marvelapp.data.model.character.CharacterComics
import com.example.marvelapp.data.model.character.CharacterInfo
import com.example.marvelapp.data.network.CharacterService
import com.example.marvelapp.data.network.RequestUtils
import io.reactivex.Single

class CharacterRepository(
    private val characterService: CharacterService,
    private val requestUtils: RequestUtils
) : CharacterDataSource {

    override fun getCharacters(limit: Int, offset: Int): Single<List<Character>> {
        return requestUtils.prepareRequest(characterService.getCharacters(limit, offset)
            .map { it.data.results })
    }

    override fun getCharacterInfo(id: Int): Single<CharacterInfo> {
        return requestUtils.prepareRequest(characterService.getCharacterInfo(id)
            .map { it.data.results.first() })
    }

    override fun getCharacterComics(id: Int): Single<List<CharacterComics>> {
        return requestUtils.prepareRequest(characterService.getCharacterComics(id)
            .map { it.data.results })
    }

}