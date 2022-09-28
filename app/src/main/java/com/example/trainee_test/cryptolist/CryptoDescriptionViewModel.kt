package com.example.trainee_test.cryptolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainee_test.use_cases.CoinDescriptionUseCase
import com.example.trainee_test.util.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoDescriptionViewModel @Inject constructor(
    private val cryptoDescriptionUseCase: CoinDescriptionUseCase
) : ViewModel(){
    private val cryptoDescrValue = MutableStateFlow(CryptoDescriptionState())
    var _cryptoDescrValue: StateFlow<CryptoDescriptionState> = cryptoDescrValue

    fun getCryptoDescrById(id: String) = viewModelScope.launch(Dispatchers.IO){
        cryptoDescriptionUseCase(id).collect{
            when(it){
                is ResponseState.Success -> {
                    cryptoDescrValue.value = CryptoDescriptionState(cryptoDescription = it.data)
                }
                is ResponseState.Loading -> {
                    cryptoDescrValue.value = CryptoDescriptionState(isLoading = true)
                }
                is ResponseState.Error -> {
                    cryptoDescrValue.value = CryptoDescriptionState(error = it.message?:"Unexpected")
                }
            }
        }
    }
}