package com.example.trainee_test.data

import com.example.trainee_test.data.dto.description.CoinDescriptionDTO
import com.example.trainee_test.data.dto.CoinListDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinGeckoApi {
    @GET("/api/v3/coins/markets?order=market_cap_desc&per_page=20&sparkline=false")
    suspend fun getAllCoins(@Query("page")page:String, @Query("vs_currency")currency: String): CoinListDTO

    @GET("/api/v3/coins/{id}")
    suspend fun getCoinById(@Path("id")id:String): CoinDescriptionDTO

}