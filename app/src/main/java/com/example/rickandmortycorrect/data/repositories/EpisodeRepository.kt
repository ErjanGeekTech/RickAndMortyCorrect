package com.example.rickandmortycorrect.data.repositories

import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmortycorrect.data.network.apiservice.EpisodeApiService
import com.example.rickandmortycorrect.data.repositories.pagingSource.EpisodePagingSource
import com.example.rickandmortycorrect.models.RickAndMortyCharacters
import com.example.rickandmortycorrect.models.RickAndMortyEpisode
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class EpisodeRepository @Inject constructor(
    private val service: EpisodeApiService
    ) {

    fun fetchEpisode(): Flow<PagingData<RickAndMortyEpisode>>{
        return Pager(config = PagingConfig(
            pageSize = 10, enablePlaceholders = false
        ), pagingSourceFactory = {
            EpisodePagingSource(service)
        }).flow
    }

    fun getIdEpisode(id: Int): MutableLiveData<RickAndMortyEpisode> {
        var character: MutableLiveData<RickAndMortyEpisode> = MutableLiveData()
        service.episode(id).enqueue(object : Callback<RickAndMortyEpisode> {
            override fun onResponse(
                call: Call<RickAndMortyEpisode>,
                response: Response<RickAndMortyEpisode>
            ) {
                character.value = response.body()
            }

            override fun onFailure(call: Call<RickAndMortyEpisode>, t: Throwable) {
                character.value = null
            }
        })
        return character
    }

}