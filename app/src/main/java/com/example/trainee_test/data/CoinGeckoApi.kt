package com.example.trainee_test.data

import com.example.trainee_test.data.dto.CoinListDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinGeckoApi {
    @GET("/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=20&sparkline=false")
    suspend fun getAllCoins(@Query("page")page:String): CoinListDTO

//    @GET("")

}