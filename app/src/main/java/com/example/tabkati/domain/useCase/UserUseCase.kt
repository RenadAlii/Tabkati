package com.example.tabkati.domain.useCase

data class UserUseCase(
    val getUserInfo: GetUserUseCase,
    val editUserName: EditUserNameUseCase
)
