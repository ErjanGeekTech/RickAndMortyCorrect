package com.example.rickandmortycorrect.ui.fragments.episodeDescription

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmortycorrect.R
import com.example.rickandmortycorrect.base.BaseFragment
import com.example.rickandmortycorrect.databinding.FragmentEpisodeDescriptionBinding
import com.example.rickandmortycorrect.ui.fragments.episode.EpisodeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeDescriptionFragment :
    BaseFragment<FragmentEpisodeDescriptionBinding, EpisodeViewModel>(
        R.layout.fragment_episode_description
    ) {
    override val binding by viewBinding(FragmentEpisodeDescriptionBinding::bind)
    override val viewModel: EpisodeViewModel by activityViewModels()
    val args: EpisodeDescriptionFragmentArgs by navArgs()

    override fun setupRequests() {
        super.setupRequests()
        fetchEpisode()
    }

    private fun fetchEpisode() {
        viewModel.getIdEpisode(args.getId).observe(viewLifecycleOwner, {
            binding.txtNameEpisodes.text = it.name
            binding.txtAirDateEpisodes.text = it.airDate
            binding.txtEpisodesEpisodes.text = it.episode
            binding.txtCreatedEpisodes.text = it.created
        })
    }
}