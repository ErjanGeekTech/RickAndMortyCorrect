package com.example.rickandmortycorrect.ui.fragments.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmortycorrect.data.repositories.CharacterRepository
import com.example.rickandmortycorrect.models.RickAndMortyCharacters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {


    fun fetchCharacters(): Flow<PagingData<RickAndMortyCharacters>> {
        return repository.fetchCharacters().cachedIn(viewModelScope)
    }


    fun fetchCharacter(id: Int) = repository.fetchCharacter(id)
}