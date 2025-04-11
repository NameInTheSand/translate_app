package com.example.transapp.data.models

import com.google.gson.annotations.SerializedName

data class TranslatedText(
    val status: Int = 200,
    val query: String = "",
    @SerializedName("business_message") val businessMessage: String = "",
    val translateTo: String = "",
    val translation: String = ""
)
