package com.example.rickandmortycorrect.models

import com.google.gson.annotations.SerializedName

data class Info(
    @SerializedName("count")
    private var count: Int,

    @SerializedName("pages")
    private val pages: Int,

    @SerializedName("next")
    val next: String,

    @SerializedName("prev")
    private val prev: Any

)