package com.example.rickandmortycorrect.data.repositories

import androidx.lifecycle.MutableLiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmortycorrect.data.db.AppDatabase
import com.example.rickandmortycorrect.data.network.apiservice.CharacterApiService
import com.example.rickandmortycorrect.data.repositories.mediator.CharactersMediator
import com.example.rickandmortycorrect.models.RickAndMortyCharacters
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val service: CharacterApiService,
    private val appDatabase: AppDatabase,
) {


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

    fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = 1, enablePlaceholders = true)
    }

    @OptIn(ExperimentalPagingApi::class)
    fun fetchCharacters(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<RickAndMortyCharacters>> {
        if (appDatabase == null) throw IllegalStateException("Database is not initialized")
        val pagingSourceFactory = { appDatabase.characterDao().getAllDoggoModel() }
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = pagingSourceFactory,
            remoteMediator = CharactersMediator(service, appDatabase)
        ).flow
    }
}