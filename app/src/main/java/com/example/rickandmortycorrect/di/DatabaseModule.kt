package com.example.rickandmortycorrect.di

import android.content.Context
import com.example.rickandmortycorrect.data.db.AppDatabase
import com.example.rickandmortycorrect.data.db.RoomClient
import com.example.rickandmortycorrect.data.db.daos.CharacterDao
import com.example.rickandmortycorrect.data.db.daos.EpisodeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    val roomClient: RoomClient = RoomClient()

    @Singleton
    @Provides
    fun provideGetDB(@ApplicationContext context: Context): AppDatabase{
        return roomClient.provideRoom(context)
    }

    @Singleton
    @Provides
    fun provideCharacters(appDatabase: AppDatabase): CharacterDao{
        return roomClient.provideCharacterDao(appDatabase)
    }
    @Singleton
    @Provides
    fun provideEpisode(appDatabase: AppDatabase): EpisodeDao{
        return roomClient.provideEpisodeDao(appDatabase)
    }


}