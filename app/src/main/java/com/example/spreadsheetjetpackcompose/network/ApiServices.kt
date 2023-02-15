package com.example.spreadsheetjetpackcompose.network

import com.example.spreadsheetjetpackcompose.model.AllDataResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiServices {
    @FormUrlEncoded
    @POST("exec?")
    suspend fun addData(@FieldMap params: Map<String, String>): Response<Any>

    @FormUrlEncoded
    @POST("exec?")
    suspend fun allData(@Field("action") action: String): AllDataResponse

    @FormUrlEncoded
    @POST("exec?")
    suspend fun deleteData(@FieldMap params: Map<String, String>): Response<Any>

    @FormUrlEncoded
    @POST("exec?")
    suspend fun readData(@FieldMap params: Map<String, String>): Response<Any>

    @FormUrlEncoded
    @POST("exec?")
    suspend fun updateData(@FieldMap params: Map<String, String>): Response<Any>
}
