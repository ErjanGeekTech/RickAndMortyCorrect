package com.example.rickandmortycorrect.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<B : ViewBinding, V : ViewModel>(
    @LayoutRes layoutId: Int
) : Fragment(layoutId) {

    protected abstract val binding: B
    protected abstract val viewModel: V


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListener()
        setupRequests()
        setupObserve()
        setupViews()
    }

    open fun setupViews() {}

    open fun setupObserve() {}

    open fun setupRequests() {}

    open fun setupListener() {}

    open fun initialize() {}

}