package com.example.spreadsheetjetpackcompose.network

import retrofit2.Response

interface ApiHelper {
    suspend fun addData(action: String ,idLokasi: String, namaLokasi: String, pathPhoto: String) : Response<Any>
}