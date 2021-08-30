package com.example.rickandmortycorrect.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmortycorrect.base.IBaseDiffModel
import com.google.gson.annotations.SerializedName

@Entity
data class RickAndMortyEpisode(
    @PrimaryKey
    @SerializedName("id")
    override val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("air_date")
    val airDate: String,

    @SerializedName("episode")
    val episode: String,


    @SerializedName("url")
    val url: String,

    @SerializedName("created")
    val created: String
): IBaseDiffModel
