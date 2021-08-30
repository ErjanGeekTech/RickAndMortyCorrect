package com.example.rickandmortycorrect.ui.fragments.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmortycorrect.data.repositories.LocationRepository
import com.example.rickandmortycorrect.models.RickAndMortyLocation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val repository: LocationRepository
) : ViewModel() {

    fun fetchEpisode(): Flow<PagingData<RickAndMortyLocation>> {
        return repository.fetchLocation().cachedIn(viewModelScope)
    }

    fun getIdLocation(id: Int) = repository.getIdLocation(id)

}
