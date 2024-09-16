package com.example.m12_mvvm.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.m12_mvvm.MainViewModelFactory
import com.example.m12_mvvm.R
import com.example.m12_mvvm.State
import com.example.m12_mvvm.databinding.FragmentMainBinding
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding!!


    private val viewModel: MainViewModel by viewModels { MainViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchButton = binding.searchButton
        val searchField = binding.searchField
        val text_view = binding.textView
        val progressBar = binding.progressBar

        //viewModel.InitContext(requireContext())

        searchField.doOnTextChanged { text, _, _, _ ->
            if (text != null)
                 viewModel.onEditTextChanged(text?.length!!)
        }

        searchButton.setOnClickListener {
            this.view?.hideKeyboard()
            viewModel.onSearchButtonClick(searchField.text.toString())
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    State.Idle -> {
                        searchButton.isEnabled = false
                        progressBar.isVisible = false
                    }
                    State.Loading -> {
                        searchField.text = null
                        searchButton.isEnabled = false
                        progressBar.isVisible = true
                    }
                    is State.Finish -> {
                        val searchResult = getString(R.string.search_result, state.searchText)
                        text_view.text = searchResult
                        progressBar.isVisible = false
                        searchButton.isEnabled = false

                    }
                }
            }
        }

        initShowMsgText()
        initEnabledButton()






    }

    private fun initEnabledButton() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.buttonVisibility.collect { buttonIsEnabled ->
                binding.searchButton.isEnabled = buttonIsEnabled
            }
        }
    }

    private fun initShowMsgText(){

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.sendMessageText.collect { msgText ->
                    binding.textView.text = msgText
                }
            }

        }
    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

}