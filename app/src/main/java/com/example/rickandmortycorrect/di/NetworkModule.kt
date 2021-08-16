package com.example.rickandmortycorrect.di

import com.example.rickandmortycorrect.data.network.RetrofitClient
import com.example.rickandmortycorrect.data.network.apiservice.CharacterApiService
import com.example.rickandmortycorrect.data.network.apiservice.EpisodeApiService
import com.example.rickandmortycorrect.data.network.apiservice.LocationApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    val retrofit: RetrofitClient = RetrofitClient()

    @Singleton
    @Provides
    fun fetchCharacterApi(): CharacterApiService {
        return retrofit.provideCharacterApiService()
    }

    @Singleton
    @Provides
    fun fetchEpisodeApi(): EpisodeApiService{
        return retrofit.provideEpisodeApiService()
    }
    @Singleton
    @Provides
    fun fetchLocationApi(): LocationApiService{
        return retrofit.provideLocationApiService()
    }

}