package com.example.marvelapp.data.model.character

import com.example.marvelapp.data.model.Image
import com.google.gson.annotations.SerializedName

data class CharacterComics (

    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String?
)