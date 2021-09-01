package com.example.rickandmortycorrect.data.network.apiservice

import com.example.rickandmortycorrect.models.RickAndMortyEpisode
import com.example.rickandmortycorrect.models.RickAndMortyLocation
import com.example.rickandmortycorrect.models.RickAndMortyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LocationApiService {

    @GET("api/location")
    suspend fun getListLocation(
        @Query("page")  page: Int,
        @Query("limit") size: Int
    ): RickAndMortyResponse<RickAndMortyLocation>

    @GET("api/location/{id}")
    fun episode(
        @Path("id") id: Int
    ): Call<RickAndMortyLocation>


}
