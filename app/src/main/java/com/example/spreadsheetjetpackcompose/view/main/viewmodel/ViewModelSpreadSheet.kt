package com.example.spreadsheetjetpackcompose.view.main.viewmodel

import com.example.spreadsheetjetpackcompose.base.BaseViewModel
import com.example.spreadsheetjetpackcompose.network.ApiHelper
import com.example.spreadsheetjetpackcompose.view.main.state.StateSpreadsheet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class ViewModelSpreadSheet @Inject constructor(
    private val repo: ApiHelper
) : BaseViewModel<StateSpreadsheet>(StateSpreadsheet.initialState) {
    suspend fun getAddData(
        action: String,
        idLokasi: String,
        namaLokasi: String,
        pathPhoto: String
    ) = withContext(Dispatchers.Default) {
        setState { copy(isLoading = true) }
        try {
            val login = repo.addData(action, idLokasi, namaLokasi, pathPhoto)
            setState { copy(isLoading = false, add = login) }
        } catch (e: HttpException) {
            setState { copy(isLoading = false, error = e.message) }
        }
    }

}