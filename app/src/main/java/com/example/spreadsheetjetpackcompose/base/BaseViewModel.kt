package com.example.spreadsheetjetpackcompose.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.updateAndGet

open class BaseViewModel<STATE : BaseState>(initialState: STATE) : ViewModel() {
    private val _state = MutableStateFlow(initialState)

    val state = _state.asStateFlow()

    val currentState: STATE get() = state.value

    fun setState(update: STATE.() -> STATE) = _state.updateAndGet(update)
}
