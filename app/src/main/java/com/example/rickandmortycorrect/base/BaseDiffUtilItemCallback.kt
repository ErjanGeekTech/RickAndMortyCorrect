package com.example.rickandmortycorrect.base

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmortycorrect.models.RickAndMortyCharacters

class BaseDiffUtilItemCallback <T: IBaseDiffModel> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(
        oldItem: T,
        newItem: T
    ): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: T,
        newItem: T
    ): Boolean {
        return oldItem == newItem
    }

}