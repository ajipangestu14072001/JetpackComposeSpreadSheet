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
        val login = try {
            repo.addData(
                action = action,
                idLokasi = idLokasi,
                namaLokasi = namaLokasi,
                pathPhoto = pathPhoto
            )
        } catch (e: HttpException) {
            setState { copy(isLoading = false, error = e.message) }
            return@withContext
        }
        setState { copy(isLoading = false, result = login) }
    }

    suspend fun getAllData(action: String) = withContext(Dispatchers.Default) {
        setState { copy(isLoading = true) }
        val data = try {
            repo.allData(action = action)
        } catch (e: HttpException) {
            setState { copy(isLoading = false, error = e.message) }
            return@withContext
        }
        setState { copy(isLoading = false, allData = data) }
    }

    suspend fun getUpdateData(
        action: String,
        idLokasi: String,
        namaLokasi: String,
        pathPhoto: String
    ) = withContext(Dispatchers.Default) {
        setState { copy(isLoading = true) }
        val update = try {
            repo.updateData(
                action = action,
                idLokasi = idLokasi,
                namaLokasi = namaLokasi,
                pathPhoto = pathPhoto
            )
        } catch (e: HttpException) {
            setState { copy(isLoading = false, error = e.message) }
            return@withContext
        }
        setState { copy(isLoading = false, result = update) }
    }

    suspend fun getDeleteData(
        action: String,
        idLokasi: String
    ) = withContext(Dispatchers.Default) {
        setState { copy(isLoading = true) }
        val data = try {
            repo.deleteData(action = action, idLokasi = idLokasi)
        } catch (e: HttpException) {
            setState { copy(isLoading = false, error = e.message) }
            return@withContext
        }
        setState { copy(isLoading = false, result = data) }
    }

    suspend fun getReadData(
        action: String,
        idLokasi: String
    ) = withContext(Dispatchers.Default) {
        setState { copy(isLoading = true) }
        val read = try {
            repo.readData(action = action, idLokasi = idLokasi)
        } catch (e: HttpException) {
            setState { copy(isLoading = false, error = e.message) }
            return@withContext
        }
        setState { copy(isLoading = false, result = read) }
    }

}