package com.example.trainee_test.cryptolist

import com.example.trainee_test.model.CryptoItem

data class CryptoListState(
    val isLoading: Boolean = false,
    val cryptoList: List<CryptoItem> = emptyList(),
    val error: String = ""
)
