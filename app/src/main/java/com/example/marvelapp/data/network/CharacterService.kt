package com.example.marvelapp.data.network

import com.example.marvelapp.data.model.base.DataWrapper
import com.example.marvelapp.data.model.character.Character
import com.example.marvelapp.data.model.character.CharacterComics
import com.example.marvelapp.data.model.character.CharacterInfo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterService {

    @GET("/v1/public/characters")
    fun getCharacters(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Single<DataWrapper<Character>>

    @GET("/v1/public/characters/{characterId}")
    fun getCharacterInfo(@Path("characterId") id: Int): Single<DataWrapper<CharacterInfo>>

    @GET("/v1/public/characters/{characterId}/comics?dateRange=2005-01-01%2C2021-01-01&orderBy=-onsaleDate&limit=10")
    fun getCharacterComics(@Path("characterId") id: Int): Single<DataWrapper<CharacterComics>>
}