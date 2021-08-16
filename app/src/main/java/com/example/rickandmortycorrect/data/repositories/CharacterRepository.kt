package com.example.rickandmortycorrect.data.repositories

import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmortycorrect.data.network.apiservice.CharacterApiService
import com.example.rickandmortycorrect.data.repositories.pagingSource.CharacterPagingSource
import com.example.rickandmortycorrect.models.RickAndMortyCharacters
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CharacterRepository @Inject constructor(var service: CharacterApiService) {

    fun fetchCharacters(): Flow<PagingData<RickAndMortyCharacters>> {
        return Pager(config = PagingConfig(
            pageSize = 10, enablePlaceholders = false
        ), pagingSourceFactory = {
            CharacterPagingSource(service)
        }).flow
    }

    fun fetchCharacter(id: Int): MutableLiveData<RickAndMortyCharacters> {
        var character: MutableLiveData<RickAndMortyCharacters> = MutableLiveData()
        service.character(id).enqueue(object : Callback<RickAndMortyCharacters> {
            override fun onResponse(
                call: Call<RickAndMortyCharacters>,
                response: Response<RickAndMortyCharacters>
            ) {
                character.value = response.body()
            }

            override fun onFailure(call: Call<RickAndMortyCharacters>, t: Throwable) {
                character.value = null
            }
        })
        return character
    }

}