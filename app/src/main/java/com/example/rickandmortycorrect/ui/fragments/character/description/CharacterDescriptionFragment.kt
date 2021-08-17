package com.example.rickandmortycorrect.ui.fragments.character.description

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.rickandmortycorrect.R
import com.example.rickandmortycorrect.base.BaseFragment
import com.example.rickandmortycorrect.databinding.FragmentCharacterDescriptionBinding
import com.example.rickandmortycorrect.ui.fragments.character.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDescriptionFragment :
    BaseFragment<FragmentCharacterDescriptionBinding, CharacterViewModel>(
        R.layout.fragment_character_description
    ) {

    override val binding by viewBinding(FragmentCharacterDescriptionBinding::bind)
    override val viewModel: CharacterViewModel by activityViewModels()
    private val args: CharacterDescriptionFragmentArgs by navArgs()

    override fun setupRequests() {
        super.setupRequests()
        fetchCharacter()
    }

    private fun fetchCharacter() {
        viewModel.fetchCharacter(args.getId).observe(viewLifecycleOwner, {
            Glide.with(binding.imageDescription)
                .load(it.image)
                .into(binding.imageDescription)
            binding.textNameDescription.text = it.name
        })
    }

}