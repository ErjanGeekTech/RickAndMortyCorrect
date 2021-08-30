package com.example.rickandmortycorrect.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rickandmortycorrect.data.db.daos.CharacterDao
import com.example.rickandmortycorrect.data.db.daos.EpisodeDao
import com.example.rickandmortycorrect.data.db.daos.LocationDao
import com.example.rickandmortycorrect.data.db.daos.RemoteKeysDao
import com.example.rickandmortycorrect.models.RemoteKeys
import com.example.rickandmortycorrect.models.RickAndMortyCharacters
import com.example.rickandmortycorrect.models.RickAndMortyEpisode
import com.example.rickandmortycorrect.models.RickAndMortyLocation

@Database(
    entities = arrayOf(
        RickAndMortyCharacters::class,
        RemoteKeys::class,
        RickAndMortyEpisode::class,
        RickAndMortyLocation::class
    ), version = 3, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao
    abstract fun locationDao(): LocationDao
    abstract fun episodeDao(): EpisodeDao
    abstract fun getRepoDao(): RemoteKeysDao
    fun lastUpdated(): Byte {
        return 3
    }

}