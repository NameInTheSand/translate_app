package com.example.transapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.transapp.data.models.TranslatedText
import com.example.transapp.data.utils.NetworkResult
import com.example.transapp.domain.repositories.translate.TranslateRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TranslateScreenViewModel(
    private val repository: TranslateRepository
) : ViewModel() {

    private val _translateScreenState = MutableStateFlow<TranslateScreenState>(
        TranslateScreenState.Loaded(
            TranslatedText()
        )
    )
    val translateScreenState = _translateScreenState.asStateFlow()
    var translateJob: Job? = null


    fun translate(query: String) {
        translateJob?.cancel()
        translateJob = viewModelScope.launch(Dispatchers.IO) {
            val response = repository.translate(query)
            _translateScreenState.emit(
                when (response) {
                    is NetworkResult.Correct<TranslatedText> -> {
                        TranslateScreenState.Loaded(response.data)
                    }

                    is NetworkResult.Error<Exception> -> {
                        TranslateScreenState.Error(
                            error = response.error
                        )
                    }
                }
            )
        }
    }
}


sealed class TranslateScreenState {
    data class Loaded(val data: TranslatedText) : TranslateScreenState()
    data class Error(val error: Exception) : TranslateScreenState()
}