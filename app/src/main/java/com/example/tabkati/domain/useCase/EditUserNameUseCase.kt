package com.example.tabkati.domain.useCase

import com.example.tabkati.domain.repository.UserFirestereRepository

class EditUserNameUseCase(private val repository: UserFirestereRepository
) {
    suspend operator fun invoke(name: String?) = repository.editUserNameInFirestore(name)
}