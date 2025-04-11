package com.example.transapp.data.utils

object NetworkCodeMapper {

    fun mapToException(errorCode:Int): IllegalStateException {
        return when(errorCode){
            404-> IllegalStateException("Bad request")
            else -> IllegalStateException("Unknown error")
        }
    }
}