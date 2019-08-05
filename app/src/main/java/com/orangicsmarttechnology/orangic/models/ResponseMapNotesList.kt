package com.orangicsmarttechnology.orangic.models

import com.google.gson.annotations.SerializedName

data class ResponseMapNotesList(
        @SerializedName("title") val title: String,
        @SerializedName("details") val details: String,
        @SerializedName("url") val url: String,
        @SerializedName("file_type") val file_type: String,
        @SerializedName("uploaded_by") val uploaded_by: String,
        @SerializedName("uploaded_date") val uploaded_date: String
        )