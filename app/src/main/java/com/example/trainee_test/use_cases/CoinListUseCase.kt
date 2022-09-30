package com.example.trainee_test.use_cases

import com.example.trainee_test.data.repository.CoinRepository
import com.example.trainee_test.model.CryptoItem
import com.example.trainee_test.util.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinListUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(page: String, currency: String): Flow<ResponseState<List<CryptoItem>>> = flow{
        try{
            emit(ResponseState.Loading())
            val cryptoItems = repository.getAllCoins(page, currency).map {
                it.toCryptoItem(currency)
            }
            emit(ResponseState.Success(cryptoItems))
        }
        catch (e: HttpException){
            emit(ResponseState.Error(e.localizedMessage?:" Unexpected error occurred"))
        }
        catch (e:IOException){
            emit(ResponseState.Error("Error occurred"))
        }
    }
}