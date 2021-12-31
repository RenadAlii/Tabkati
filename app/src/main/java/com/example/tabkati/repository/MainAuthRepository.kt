package com.example.tabkati.repository

import com.example.tabkati.utils.State.Failure
import com.example.tabkati.utils.State.Loading
import com.example.tabkati.utils.State.Success
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainAuthRepository @Inject constructor(
    private val googleSignInClient: GoogleSignInClient,
    private val auth: FirebaseAuth
){
    fun signOut() = flow {

        try {
            emit(Loading)
            googleSignInClient.signOut().await().also {
                emit(Success(it))
            }
            auth.signOut()
        } catch (e: Exception) {
            emit(Failure(e.message ?: "Unexpected error!"))
        }
    }

    @ExperimentalCoroutinesApi
    fun getFirebaseAuthState(): Flow<Boolean> = callbackFlow  {
        val authStateListener = FirebaseAuth.AuthStateListener { auth ->
            trySend(auth.currentUser == null)
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose {
            auth.removeAuthStateListener(authStateListener)
        }
    }
}