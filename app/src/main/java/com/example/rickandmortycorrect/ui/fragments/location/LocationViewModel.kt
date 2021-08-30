package com.example.rickandmortycorrect.ui.fragments.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickandmortycorrect.data.repositories.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class LocationViewModel @Inject constructor(
    private val repository: LocationRepository
    ): ViewModel() {

    fun fetchLocation() = repository.fetchLocation().cachedIn(viewModelScope)

    fun getIdLocation(id: Int) = repository.getIdLocation(id)

}
