package com.example.rickandmortycorrect.ui.fragments.episode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickandmortycorrect.data.repositories.EpisodeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val repository: EpisodeRepository
    ): ViewModel() {

    fun fetchEpisode() = repository.fetchEpisode().cachedIn(viewModelScope)

    fun getIdEpisode(id: Int) = repository.getIdEpisode(id)

}