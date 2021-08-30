package com.example.rickandmortycorrect.data.repositories

import androidx.lifecycle.MutableLiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmortycorrect.data.db.AppDatabase
import com.example.rickandmortycorrect.data.network.apiservice.LocationApiService
import com.example.rickandmortycorrect.data.repositories.mediator.LocationMediator
import com.example.rickandmortycorrect.models.RickAndMortyLocation
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class LocationRepository @Inject constructor(
    private val service: LocationApiService,
    private val appDatabase: AppDatabase
) {

//    fun fetchLocation(): Flow<PagingData<RickAndMortyLocation>>{
//        return Pager(config = PagingConfig(
//            pageSize = 10, enablePlaceholders = false
//        ), pagingSourceFactory = {
//            LocationPagingSource(service)
//        }).flow
//    }

    fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = 1, enablePlaceholders = true)
    }

    @OptIn(ExperimentalPagingApi::class)
    fun fetchLocation(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<RickAndMortyLocation>> {
        if (appDatabase == null) throw IllegalStateException("Database is not initialized")

        val pagingSourceFactory = { appDatabase.locationDao().getAllDoggoModel() }
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = pagingSourceFactory,
            remoteMediator = LocationMediator(service, appDatabase)
        ).flow
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