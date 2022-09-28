package com.example.trainee_test.data.repository

import com.example.trainee_test.data.dto.description.CoinDescriptionDTO
import com.example.trainee_test.data.dto.CoinListDTO

interface CoinRepository {

    suspend fun getAllCoins(page: String):CoinListDTO

    suspend fun getCoinById(id: String): CoinDescriptionDTO
}