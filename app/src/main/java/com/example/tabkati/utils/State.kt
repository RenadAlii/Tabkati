package com.example.tabkati.utils


sealed class State<out T> {
    object Loading: State<Nothing>()

    data class Success<out T>(
        val data: T
    ): State<T>()

    data class Failure(
        val errorMessage: String
    ): State<Nothing>()
}
