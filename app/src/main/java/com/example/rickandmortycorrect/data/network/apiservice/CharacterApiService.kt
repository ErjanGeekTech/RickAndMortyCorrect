package com.example.rickandmortycorrect.data.network.apiservice

import com.example.rickandmortycorrect.models.RickAndMortyCharacters
import com.example.rickandmortycorrect.models.RickAndMortyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApiService {

    @GET("api/character")
    suspend fun getListCharacter(
        @Query("page") page: Int
    ): RickAndMortyResponse<RickAndMortyCharacters>

    @GET("api/character/{id}")
    fun character(
        @Path("id") id: Int
    ): Call<RickAndMortyCharacters>

}