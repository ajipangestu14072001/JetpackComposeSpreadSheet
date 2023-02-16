package com.example.spreadsheetjetpackcompose.view.component.state

import androidx.compose.runtime.Immutable
import com.example.spreadsheetjetpackcompose.base.BaseState
import com.example.spreadsheetjetpackcompose.model.AllDataResponse
import retrofit2.Response

@Immutable
data class StateSpreadsheet constructor(
    var isLoading: Boolean = true,
    val result: Response<Any>? = null,
    val allData: AllDataResponse? = null,
    val error: String? = null
) : BaseState {
    companion object {
        val initialState: StateSpreadsheet
            get() = StateSpreadsheet()
    }
}
