package com.example.rickandmortycorrect.models

import com.google.gson.annotations.SerializedName

data class RickAndMortyEpisode(
    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var name: String,

    @SerializedName("air_date")
    var airDate: String,

    @SerializedName("episode")
    var episode: String,


    @SerializedName("url")
    var url: String,

    @SerializedName("created")
    var created: String
)
