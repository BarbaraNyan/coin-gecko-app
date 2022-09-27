package com.example.trainee_test.data.dto

import com.example.trainee_test.model.CryptoItem

data class CoinListDTOItem (
    val id: String,
    val name: String,
    val symbol: String,
    val current_price: Double,
    val price_change_percentage_24h: Double,
    val image: String
){
    fun toCryptoItem(): CryptoItem{
        return CryptoItem(id = id, cryptoNameFull = name, cryptoNameShort = symbol,
            cryptoPrice = current_price, cryptoPercent = price_change_percentage_24h,
            cryptoImage = image)
    }
}