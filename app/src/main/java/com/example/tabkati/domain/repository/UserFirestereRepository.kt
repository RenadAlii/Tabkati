package com.example.tabkati.domain.repository

import com.example.tabkati.model.User
import com.example.tabkati.utils.State
import kotlinx.coroutines.flow.Flow

interface UserFirestereRepository {

     fun getUserFromFirestore(): Flow<User>
    suspend fun editUserNameInFirestore(name: String?): Flow<Unit>


}