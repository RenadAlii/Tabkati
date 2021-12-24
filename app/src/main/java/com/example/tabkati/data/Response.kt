package com.example.tabkati.data

import androidx.lifecycle.LiveData

sealed class Response<out T> {
    object Loading : Response<Nothing>()

    data class Success<out T>(
        val data: T
    ) : Response<T>()

    data class Failure(
        val errorMessage: String
    ) : Response<Nothing>()


}
