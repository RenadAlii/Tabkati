package com.example.tabkati.domain.repository

import com.example.tabkati.model.User
import kotlinx.coroutines.flow.Flow

interface UserFirestereRepository {

    fun getUserFromFirestore(): Flow<User>

}