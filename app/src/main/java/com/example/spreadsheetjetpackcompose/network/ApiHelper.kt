package com.example.spreadsheetjetpackcompose.network

import com.example.spreadsheetjetpackcompose.model.AllDataResponse
import retrofit2.Response

interface ApiHelper {
    suspend fun addData(action: String ,idLokasi: String, namaLokasi: String, pathPhoto: String) : Response<Any>
    suspend fun allData(action: String) : AllDataResponse
    suspend fun deleteData(action: String, idLokasi: String) : Response<Any>
    suspend fun readData(action: String, idLokasi: String) : Response<Any>
    suspend fun updateData(action: String ,idLokasi: String, namaLokasi: String, pathPhoto: String) : Response<Any>
}
