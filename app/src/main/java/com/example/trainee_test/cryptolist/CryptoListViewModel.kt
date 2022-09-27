package com.example.trainee_test.cryptolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainee_test.use_cases.CoinListUseCase
import com.example.trainee_test.util.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoListViewModel @Inject constructor(
    private val coinUseCase: CoinListUseCase
): ViewModel() {
    private val cryptoListValue = MutableStateFlow(CryptoListState())
    var _cryptoListValue: StateFlow<CryptoListState> = cryptoListValue

    fun getAllCoins(page:String) = viewModelScope.launch(Dispatchers.IO) {
        coinUseCase(page).collect{
            when(it){
                is ResponseState.Success -> {
                    cryptoListValue.value = CryptoListState(cryptoList = it.data?: emptyList())
                }
                is ResponseState.Loading -> {
                    cryptoListValue.value = CryptoListState(isLoading = true)
                }
                is ResponseState.Error -> {
                    cryptoListValue.value = CryptoListState(error = it.message?:"Unexpected")
                }
            }
        }
    }
}