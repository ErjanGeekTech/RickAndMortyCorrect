package com.example.rickandmortycorrect.data.repositories

import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmortycorrect.data.network.apiservice.LocationApiService
import com.example.rickandmortycorrect.data.repositories.pagingSource.LocationPagingSource
import com.example.rickandmortycorrect.models.RickAndMortyEpisode
import com.example.rickandmortycorrect.models.RickAndMortyLocation
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class LocationRepository @Inject constructor(val service: LocationApiService) {

    fun fetchLocation(): Flow<PagingData<RickAndMortyLocation>>{
        return Pager(config = PagingConfig(
            pageSize = 10, enablePlaceholders = false
        ), pagingSourceFactory = {
            LocationPagingSource(service)
        }).flow
    }

    fun getIdLocation(id: Int): MutableLiveData<RickAndMortyLocation> {
        var character: MutableLiveData<RickAndMortyLocation> = MutableLiveData()
        service.episode(id).enqueue(object : Callback<RickAndMortyLocation> {
            override fun onResponse(
                call: Call<RickAndMortyLocation>,
                response: Response<RickAndMortyLocation>
            ) {
                character.value = response.body()
            }

            override fun onFailure(call: Call<RickAndMortyLocation>, t: Throwable) {
                character.value = null
            }
        })
        return character
    }

}