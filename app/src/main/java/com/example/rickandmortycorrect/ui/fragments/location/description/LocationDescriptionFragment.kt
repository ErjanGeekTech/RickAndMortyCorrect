package com.example.rickandmortycorrect.ui.fragments.location.description

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmortycorrect.R
import com.example.rickandmortycorrect.base.BaseFragment
import com.example.rickandmortycorrect.databinding.FragmentLocationDescriptionBinding
import com.example.rickandmortycorrect.ui.fragments.location.LocationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationDescriptionFragment :
    BaseFragment<FragmentLocationDescriptionBinding, LocationViewModel>(
        R.layout.fragment_location_description
    ) {
    override val binding by viewBinding(FragmentLocationDescriptionBinding::bind)
    override val viewModel: LocationViewModel by activityViewModels()
    val args: LocationDescriptionFragmentArgs by navArgs()

    override fun setupRequests() {
        super.setupRequests()
        getIdLocation()
    }

    private fun getIdLocation() {
        viewModel.getIdLocation(args.getIdLocation).observe(viewLifecycleOwner, {
            binding.txtNameLocation.text = it.name
            binding.typeLocationTxt.text = it.type
            binding.txtDimensionLocation.text = it.dimension
            binding.txtCreatedLocation.text = it.created
        })
    }


}