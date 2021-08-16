package com.example.rickandmortycorrect.ui.fragments.character

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmortycorrect.R
import com.example.rickandmortycorrect.base.BaseFragment
import com.example.rickandmortycorrect.databinding.FragmentCharacterBinding
import com.example.rickandmortycorrect.ui.adapters.CharacterAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterFragment :
    BaseFragment<FragmentCharacterBinding, CharacterViewModel>(
        R.layout.fragment_character
    ) {


    private val characterAdapter: CharacterAdapter = CharacterAdapter(
        this::onItemClick,
        this::onItemLongClick
    )
    override val viewModel: CharacterViewModel by activityViewModels()
    override val binding by viewBinding(FragmentCharacterBinding::bind)


    override fun setupRequests() {
        super.setupRequests()
        fetchCharacters()
    }


    override fun setupViews() {
        super.setupViews()
        setupRecycler()

    }

    private fun setupRecycler() {
        binding.rv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = characterAdapter
        }

    }

    private fun fetchCharacters() {
        lifecycleScope.launch {
            viewModel.fetchCharacters().collectLatest {
                characterAdapter.submitData(it)

            }
        }
    }


    private fun onItemClick(id: Int) {
        findNavController().navigate(
            CharacterFragmentDirections.actionCharacterFragmentToCharacterDescriptionFragment(id)
        )
    }
    private fun onItemLongClick(photo: String) {
        findNavController().navigate(
            CharacterFragmentDirections.actionCharacterFragmentToDlalogFragment(photo)
        )
    }
}


