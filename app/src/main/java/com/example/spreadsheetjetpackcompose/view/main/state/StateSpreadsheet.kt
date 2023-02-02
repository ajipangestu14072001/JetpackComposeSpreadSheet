package com.example.spreadsheetjetpackcompose.view.main.state

import androidx.compose.runtime.Immutable
import com.example.spreadsheetjetpackcompose.base.BaseState
import retrofit2.Response

@Immutable
data class StateSpreadsheet constructor(
    var isLoading: Boolean = true,
    val add: Response<Any>? = null,
    val error: String? = null
) : BaseState {
    companion object {
        val initialState: StateSpreadsheet
            get() = StateSpreadsheet()
    }
}
