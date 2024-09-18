package com.example.m12_mvvm.ui.main

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.m12_mvvm.R
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: MainRepository
) : ViewModel() {

    private val _state = MutableStateFlow<State>(State.Error(null))
    val state = _state.asStateFlow()

    private val _error = Channel<String>()
    val error = _error.receiveAsFlow()

    private val _searchs = MutableStateFlow(Searchs())
    val searchs = _searchs.asStateFlow()

    private fun onFindClick() {
        Log.d("GB!","Что-то ищем...")
        viewModelScope.launch {
            val query = searchs.value.query
            _state.value = State.Searching
            try {
                repository.getData(query)
                _state.value = State.UnSuccess
            } catch (e: Exception) {
                _error.send(e.toString())
                _state.value = State.Error(null)
            }
        }
    }
    fun onEditQuery(query: String, v: View) {
        viewModelScope.launch {
            val isIncorrectQuery = (query.isBlank() || (query.length < 3))
            val queryError: String?
            if (isIncorrectQuery) {
                queryError = v.context.getString(R.string.query_is_incorrect)
                _state.value = State.Error(queryError)
            }
            else {
                _state.value = State.Success
                onFindClick()
            }
        }
    }
}