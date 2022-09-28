package com.example.trainee_test.use_cases

import com.example.trainee_test.data.repository.CoinRepository
import com.example.trainee_test.model.CryptoDescription
import com.example.trainee_test.model.CryptoItem
import com.example.trainee_test.util.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinDescriptionUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(id: String): Flow<ResponseState<CryptoDescription>> = flow{
        try{
            emit(ResponseState.Loading())
            val cryptoDescription = repository.getCoinById(id).toCryptoDescription()
            emit(ResponseState.Success(cryptoDescription))
        }
        catch (e: HttpException){
            emit(ResponseState.Error(e.localizedMessage?:"Unexpected error"))
        }
        catch (e: IOException){
            emit(ResponseState.Error("Error"))
        }
    }
}