package com.example.rickandmortycorrect.data.db.daos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmortycorrect.models.RickAndMortyCharacters

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RickAndMortyCharacters>)

    @Query("SELECT * FROM characters")
    fun getAllDoggoModel(): PagingSource<Int, RickAndMortyCharacters>

    @Query("DELETE FROM characters")
    suspend fun clearRemoteKeys()


}