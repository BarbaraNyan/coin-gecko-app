package com.example.trainee_test.data.repository

import com.example.trainee_test.data.dto.CoinListDTO

interface CoinRepository {

    suspend fun getAllCoins(page: String):CoinListDTO
}