package com.example.marvelapp.data.model.character

import com.google.gson.annotations.SerializedName

class CharacterInfo(
    @SerializedName("description")
    val description: String?
)