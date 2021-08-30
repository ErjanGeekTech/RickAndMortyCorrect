package com.example.rickandmortycorrect.models

import com.example.rickandmortycorrect.base.IBaseDiffModel
import com.google.gson.annotations.SerializedName

data class RickAndMortyCharacters(
    @SerializedName("id")
    override var id: Int,

    @SerializedName("name")
    var name: String,

    @SerializedName("status")
    var status: String,

    @SerializedName("image")
    var image: String
): IBaseDiffModel