package com.example.spreadsheetjetpackcompose.model

data class AllDataResponse(
    val records: List<Record>
)

data class Record(
    val ID_LOKASI: String,
    val NAMA_LOKASI: String,
    val PHOTO: String,
    val TIMESTAMP: String
)