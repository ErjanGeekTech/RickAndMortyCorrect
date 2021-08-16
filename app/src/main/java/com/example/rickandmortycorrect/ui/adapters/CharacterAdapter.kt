package com.example.rickandmortycorrect.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmortycorrect.databinding.ItemCharacterBinding
import com.example.rickandmortycorrect.models.RickAndMortyCharacters

class CharacterAdapter(
    val onItemClick: (id: Int) -> Unit ,
    val onItemLongClick: (photo: String) -> Unit
) : PagingDataAdapter<RickAndMortyCharacters, CharacterAdapter.CharacterViewHolder>(
    differCallback
) {


    lateinit var binding: ItemCharacterBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }


    inner class CharacterViewHolder(
        private val binding: ItemCharacterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                getItem(absoluteAdapterPosition)?.apply { onItemClick(id) }
            }

            binding.imageItemCharacter.setOnLongClickListener{
                getItem(absoluteAdapterPosition)?.apply { onItemLongClick(image) }
                 false
            }
        }


        fun onBind(rickAndMortyCharacters: RickAndMortyCharacters) {
            Glide
                .with(binding.imageItemCharacter)
                .load(rickAndMortyCharacters.image)
                .into(binding.imageItemCharacter)
            binding.textItemCharacter.text = rickAndMortyCharacters.name
        }

    }

    companion object {
        val differCallback = object : DiffUtil.ItemCallback<RickAndMortyCharacters>() {
            override fun areItemsTheSame(
                oldItem: RickAndMortyCharacters,
                newItem: RickAndMortyCharacters
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: RickAndMortyCharacters,
                newItem: RickAndMortyCharacters
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

}