package com.example.rickandmortycorrect.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class RickAndMortyResponse<T>(
    @SerializedName("info") var info: Info,
    @SerializedName("results")
    var results: ArrayList<T>
)