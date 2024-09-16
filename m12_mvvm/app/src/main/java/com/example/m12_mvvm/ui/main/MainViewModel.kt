package com.example.m12_mvvm.ui.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.m12_mvvm.MainRepository
import com.example.m12_mvvm.R
import com.example.m12_mvvm.State
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: MainRepository
) : ViewModel() {

    private lateinit var  context : Context;

    private val _state = MutableStateFlow<State>(State.Idle)
    val state = _state.asStateFlow()

    private val _buttonVisibility = Channel<Boolean>()
    val buttonVisibility = _buttonVisibility.receiveAsFlow()

    private val _sendMessageText = Channel<String>()
    val sendMessageText = _sendMessageText.receiveAsFlow()

    fun InitContext(context : Context){
        this.context = context;
    }



    fun onEditTextChanged(textSize: Int) {
        viewModelScope.launch {
            _buttonVisibility.send(textSize >= 3)
        }
    }



    fun onSearchButtonClick(searchText: String) {
        viewModelScope.launch {
            _state.value = State.Loading
            val result = repository.search(searchText)
            _state.value = State.Finish(result)
            viewModelScope.launch {
                  _sendMessageText.send(context.resources.getString(R.string.search_result, searchText))
           }
        }
    }
}
