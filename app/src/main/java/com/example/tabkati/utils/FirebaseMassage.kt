package com.example.tabkati.utils

sealed class FirebaseMassage<out T> {
    data class Massage(
        val msg: String
    ): State<Nothing>()
}

