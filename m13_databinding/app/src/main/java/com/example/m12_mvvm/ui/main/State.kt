package com.example.m12_mvvm.ui.main

sealed class State(
    val isSearching: Boolean = false,
    open val queryError: String? = null
) {
    object Searching: State(isSearching = true)
    object Success: State()
    object UnSuccess: State()
    data class Error(
        override val queryError: String?
    ): State(
        queryError = queryError
    )
}