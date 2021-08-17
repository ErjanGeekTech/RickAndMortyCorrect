package com.example.rickandmortycorrect.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortycorrect.base.BaseDiffUtilItemCallback
import com.example.rickandmortycorrect.databinding.ItemLocationBinding
import com.example.rickandmortycorrect.models.RickAndMortyLocation

class LocationAdapter(
    val onItemClick: (id: Int) -> Unit
) : PagingDataAdapter<RickAndMortyLocation, LocationAdapter.LocationViewHolder>(
    BaseDiffUtilItemCallback<RickAndMortyLocation>()
) {

   private lateinit var binding: ItemLocationBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LocationAdapter.LocationViewHolder {
        binding = ItemLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LocationViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: LocationAdapter.LocationViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class LocationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                getItem(absoluteAdapterPosition)?.apply { onItemClick(id) }
            }
        }

        fun onBind(rickAndMortyLocations: RickAndMortyLocation) {
            binding.txtNameLocation.text = rickAndMortyLocations.name
            binding.typeLocationTxt.text = rickAndMortyLocations.type
            binding.txtDimensionLocation.text = rickAndMortyLocations.dimension
            binding.txtCreatedLocation.text = rickAndMortyLocations.created
        }


    }
}