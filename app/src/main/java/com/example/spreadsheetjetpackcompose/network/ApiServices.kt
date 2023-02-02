package com.example.spreadsheetjetpackcompose.network

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiServices {
    @POST("exec?")
    @FormUrlEncoded
    suspend fun getAddData(
        @Field("action") action: String,
        @Field("idLokasi") idLokasi: String,
        @Field("namaLokasi") namaLokasi: String,
        @Field("pathPhoto") pathPhoto: String
    ): Response<Any>

}
