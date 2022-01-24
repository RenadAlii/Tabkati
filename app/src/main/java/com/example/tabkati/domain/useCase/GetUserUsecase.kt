package com.example.tabkati.domain.useCase

import com.example.tabkati.domain.repository.UserFirestereRepository

class GetUserUseCase(  private val repository: UserFirestereRepository
) {
    operator fun invoke() = repository.getUserFromFirestore()
}