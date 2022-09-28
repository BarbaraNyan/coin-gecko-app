package com.example.trainee_test.data.repository

import com.example.trainee_test.data.CoinGeckoApi
import com.example.trainee_test.data.dto.description.CoinDescriptionDTO
import com.example.trainee_test.data.dto.CoinListDTO
import javax.inject.Inject


class CoinRepositoryImpl @Inject constructor(
    private val coinApi: CoinGeckoApi
): CoinRepository {
    override suspend fun getAllCoins(page: String): CoinListDTO {
        return coinApi.getAllCoins(page = page)
    }

    override suspend fun getCoinById(id: String): CoinDescriptionDTO {
        return coinApi.getCoinById(id = id)
    }
}