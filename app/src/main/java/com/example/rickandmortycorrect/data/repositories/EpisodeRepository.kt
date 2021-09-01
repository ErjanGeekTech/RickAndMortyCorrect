package com.example.rickandmortycorrect.data.repositories

import androidx.lifecycle.MutableLiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmortycorrect.data.db.AppDatabase
import com.example.rickandmortycorrect.data.network.apiservice.EpisodeApiService
import com.example.rickandmortycorrect.data.repositories.mediator.EpisodeMediator
import com.example.rickandmortycorrect.models.RickAndMortyEpisode
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class EpisodeRepository @Inject constructor(
    private val service: EpisodeApiService,
    private val appDatabase: AppDatabase,
) {


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

    fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = 1, enablePlaceholders = true)
    }

    @OptIn(ExperimentalPagingApi::class)
    fun fetchEpisode(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<RickAndMortyEpisode>> {
        if (appDatabase == null) throw IllegalStateException("Database is not initialized")

        val pagingSourceFactory = { appDatabase.episodeDao().getAllDoggoModel() }
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = pagingSourceFactory,
            remoteMediator = EpisodeMediator(service, appDatabase)
        ).flow
    }


}