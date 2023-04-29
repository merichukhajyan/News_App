package com.example.newsapp.presentation.homeScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.remote.Resource
import com.example.newsapp.domain.useCase.GetNewsByCountryUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class NewsViewModel(
    private val getNewsByCountryUseCase: GetNewsByCountryUseCase,
): ViewModel(

) {

    private val _state = mutableStateOf(NewsState())
    val state: State<NewsState> = _state

    init {
        loadNews()
    }

    private fun loadNews(country: String = "us"){
        getNewsByCountryUseCase(country).onEach { result ->
            when(result){
                is Resource.Error -> {
                    _state.value = NewsState(error = result.message ?: "unexpected", isLoading = false)
                }
                is Resource.Loading -> {
                    _state.value = NewsState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = NewsState(
                        isLoading = false,
                        news = result.data
                    )
                }
                }
            }.launchIn(viewModelScope)
        }

    fun changeSearchBarValue(value: String){
        _state.value = _state.value.copy(searchValue = value)
    }

    }
