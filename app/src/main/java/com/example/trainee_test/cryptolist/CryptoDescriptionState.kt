package com.example.trainee_test.cryptolist

import com.example.trainee_test.model.CryptoDescription
import com.example.trainee_test.model.CryptoItem

data class CryptoDescriptionState(
    val isLoading: Boolean = false,
    val cryptoDescription: CryptoDescription? = null,
    val error: String = ""
)
