package com.example.spreadsheetjetpackcompose.impl

import com.example.spreadsheetjetpackcompose.network.ApiHelper
import com.example.spreadsheetjetpackcompose.network.ApiServices
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val api: ApiServices
) : ApiHelper {

    override suspend fun addData(
        action: String,
        idLokasi: String,
        namaLokasi: String,
        pathPhoto: String
    ) = api.addData(
        params = mapOf(
            "action" to action,
            "idLokasi" to idLokasi,
            "namaLokasi" to namaLokasi,
            "pathPhoto" to pathPhoto
        )
    )

    override suspend fun allData(action: String) = api.allData(action = action)

    override suspend fun deleteData(action: String, idLokasi: String) =
        api.deleteData(
            params = mapOf(
                "action" to action,
                "idLokasi" to idLokasi
            )
        )

    override suspend fun readData(action: String, idLokasi: String) =
        api.readData(
            params = mapOf(
                "action" to action,
                "idLokasi" to idLokasi
            )
        )

    override suspend fun updateData(
        action: String,
        idLokasi: String,
        namaLokasi: String,
        pathPhoto: String
    ) = api.updateData(
        params = mapOf(
            "action" to action,
            "idLokasi" to idLokasi,
            "namaLokasi" to namaLokasi,
            "pathPhoto" to pathPhoto
        )
    )
}