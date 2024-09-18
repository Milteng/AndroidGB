package com.example.m12_mvvm.ui.main

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.m12_mvvm.R
import com.example.m12_mvvm.databinding.MainFragmentBinding
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {
    private lateinit var binding: MainFragmentBinding
    private val viewModel: MainViewModel by viewModels{MainViewModelFactory()}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewLifecycleOwner.lifecycleScope
            .launchWhenStarted {
                viewModel.state
                    .collect { state ->
                        when (state) {
                            State.Searching -> {
                                binding.progress.isVisible = true
                                binding.queryLayout.error = null
                                binding.searchResult.text = getString(R.string.result_default)
                            }
                            State.Success -> {
                                binding.progress.isVisible = false
                                binding.queryLayout.error = null
                                binding.searchResult.text = getString(R.string.result_default)
                            }
                            State.UnSuccess -> {
                                binding.progress.isVisible = false
                                binding.queryLayout.error = null
                                binding.searchResult.text = getString(R.string.result_fake).replace("!query!", binding.query.text.toString())
                            }
                            is State.Error -> {
                                binding.progress.isVisible = false
                                binding.queryLayout.error = state.queryError
                                binding.searchResult.text = getString(R.string.result_default)
                            }
                        }
                    }
            }

        viewLifecycleOwner.lifecycleScope
            .launchWhenStarted {
                viewModel.error
                    .collect {message ->
                        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
                    }
            }
    }
}