package com.example.trainee_test.model

data class CryptoItem (
    val id: String,
    var cryptoNameFull: String = "",
    var cryptoNameShort: String = "",
    var cryptoPrice: Double,
    var cryptoPercent: Double
)