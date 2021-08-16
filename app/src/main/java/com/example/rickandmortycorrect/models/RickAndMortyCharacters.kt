package com.example.rickandmortycorrect.models

import com.google.gson.annotations.SerializedName

data class RickAndMortyCharacters(
    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var name: String,

    @SerializedName("status")
    var status: String,

    @SerializedName("image")
    var image: String
)