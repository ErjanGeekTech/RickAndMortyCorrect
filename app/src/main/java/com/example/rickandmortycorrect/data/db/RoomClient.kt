package com.example.rickandmortycorrect.data.db

import android.content.Context
import androidx.room.Room
import com.example.rickandmortycorrect.data.db.daos.CharacterDao
import com.example.rickandmortycorrect.data.db.daos.EpisodeDao

class RoomClient {

    fun provideRoom(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "rickAndMorty.db"
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }


    fun provideCharacterDao(appDatabase: AppDatabase): CharacterDao {
        return appDatabase.characterDao()
    }

    fun provideEpisodeDao(appDatabase: AppDatabase): EpisodeDao {
        return appDatabase.episodeDao()
    }
//    fun provideLocationDao(appDatabase: AppDatabase): LocationDao{
//        return appDatabase.locationDao()
//    }


}