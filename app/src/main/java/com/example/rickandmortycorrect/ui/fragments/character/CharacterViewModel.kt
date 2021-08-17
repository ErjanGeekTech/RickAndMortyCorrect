package com.example.rickandmortycorrect.ui.fragments.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickandmortycorrect.data.repositories.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: CharacterRepository
    ) : ViewModel() {

    fun fetchCharacters() = repository.fetchCharacters().cachedIn(viewModelScope)

    fun fetchCharacter(id: Int) = repository.fetchCharacter(id)
}