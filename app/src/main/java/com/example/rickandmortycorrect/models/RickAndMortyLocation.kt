package com.example.rickandmortycorrect.models

import com.google.gson.annotations.SerializedName

data class RickAndMortyLocation(
    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var name: String,

    @SerializedName("type")
    var type: String,

    @SerializedName("dimension")
    var dimension: String,

    @SerializedName("url")
    var url: String,

    @SerializedName("created")
    var created: String
)