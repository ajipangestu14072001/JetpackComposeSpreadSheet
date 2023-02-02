package com.example.spreadsheetjetpackcompose.impl

import com.example.spreadsheetjetpackcompose.network.ApiHelper
import retrofit2.Response
import javax.inject.Inject

class RepoImpl @Inject constructor(private val remoteDS: ApiHelperImpl) : ApiHelper {
    override suspend fun addData(action: String,idLokasi: String, namaLokasi: String, pathPhoto: String): Response<Any> {
        return remoteDS.addData(action = action ,idLokasi = idLokasi, namaLokasi = namaLokasi, pathPhoto = pathPhoto)
    }

}