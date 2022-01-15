package com.example.tabkati.utils

sealed class FirebaseMassage<out T> {

    object Loading: FirebaseMassage<Nothing>()
    data class Massage(
        val msg: String
    ): FirebaseMassage<Nothing>()
}

