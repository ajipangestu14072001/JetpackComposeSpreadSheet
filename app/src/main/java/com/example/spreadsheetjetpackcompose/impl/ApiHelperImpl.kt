package com.example.spreadsheetjetpackcompose.impl

import com.example.spreadsheetjetpackcompose.network.ApiHelper
import com.example.spreadsheetjetpackcompose.network.ApiServices
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val api: ApiServices
) : ApiHelper {
    override suspend fun addData(
        action: String,
        idLokasi: String,
        namaLokasi: String,
        pathPhoto: String
    ): Response<Any> {
        return api.getAddData(
            action = action,
            idLokasi = idLokasi,
            namaLokasi = namaLokasi,
            pathPhoto = pathPhoto
        )
    }
}