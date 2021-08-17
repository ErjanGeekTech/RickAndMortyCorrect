package com.example.rickandmortycorrect.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortycorrect.base.BaseDiffUtilItemCallback
import com.example.rickandmortycorrect.databinding.ItemEpisodeBinding
import com.example.rickandmortycorrect.models.RickAndMortyEpisode

class EpisodeAdapter(
    val onItemClick: (id: Int) -> Unit
) : PagingDataAdapter<RickAndMortyEpisode, EpisodeAdapter.EpisodeViewHolder>(
    BaseDiffUtilItemCallback<RickAndMortyEpisode>()
) {

    private lateinit var binding: ItemEpisodeBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        binding = ItemEpisodeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EpisodeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }


    inner class EpisodeViewHolder(
        private val binding: ItemEpisodeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                getItem(absoluteAdapterPosition)?.apply { onItemClick(id) }
            }
        }

        fun onBind(rickAndMortyEpisodes: RickAndMortyEpisode) {
            binding.txtNameEpisodes.text = rickAndMortyEpisodes.name
            binding.txtAirDateEpisodes.text = rickAndMortyEpisodes.airDate
            binding.txtEpisodesEpisodes.text = rickAndMortyEpisodes.episode
            binding.txtCreatedEpisodes.text = rickAndMortyEpisodes.created
        }

    }
}