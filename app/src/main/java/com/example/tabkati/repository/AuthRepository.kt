package com.example.tabkati.repository

import androidx.lifecycle.liveData
import com.example.tabkati.data.AuthFirebaseRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val dataSource: AuthFirebaseRemoteDataSource,
){


    // fun to signOut.
   suspend fun signOut() = flow {
        dataSource.signOut().collect { response ->
            emit(response)
        }
    }

    // fun to get the user auth state if the user is signIn in or not.
    @ExperimentalCoroutinesApi
   suspend fun getAuthState() = flow {
        dataSource.getFirebaseAuthState().collect { response ->
            emit(response)
        }
    }

    // fun to sign the user with google.
    fun signInWithGoogle(idToken: String) = flow {
        dataSource.firebaseSignInWithGoogle(idToken).collect { response ->
            emit(response)
        }
    }


    // fun to create user in fireStore if the user sign up using google.
   suspend fun createUser() = flow {
        dataSource.createUserInFireStore().collect { response ->
            emit(response)
        }
    }

    // fun to create user in fireStore if the user sign up using email.
    suspend fun createUser(name: String): Flow<Boolean> =
        dataSource.createUserInFireStoreh(name)





}