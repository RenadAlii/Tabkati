package com.example.tabkati.domain.use_case

data class UserUseCase(
    val getUserInfo: GetUserUseCase,
    val editUserName: EditUserNameUseCase
)
