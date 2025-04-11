package com.example.transapp.data.utils

sealed class NetworkResult<D, E> {
    data class Correct<D>(val data: D) : NetworkResult<D, IllegalStateException>()
    data class Error<E>(val error: E) : NetworkResult<Nothing, E>()
}