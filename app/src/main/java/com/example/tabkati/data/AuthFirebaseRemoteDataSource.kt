package com.example.tabkati.data

import android.util.Log
import com.example.tabkati.model.RecipesModel
import com.example.tabkati.utils.State.*
import com.example.tabkati.utils.Constants.EMAIL
import com.example.tabkati.utils.Constants.FAVOURITE
import com.example.tabkati.utils.Constants.NAME
import com.example.tabkati.utils.Constants.USERS_REF
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

//Singleton to create one instanse of this class.
@Singleton
class AuthFirebaseRemoteDataSource @Inject constructor(
    private val auth: FirebaseAuth,
    @Named(USERS_REF) private val usersReference: CollectionReference,
    private val googleSignInClient: GoogleSignInClient
) {



   suspend fun signOut() = flow {
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
    suspend fun getFirebaseAuthState(): Flow<Boolean> = callbackFlow  {
        val authStateListener = FirebaseAuth.AuthStateListener { auth ->
            trySend(auth.currentUser == null)
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose {
            auth.removeAuthStateListener(authStateListener)
        }
    }


    suspend fun firebaseSignInWithGoogle(idToken: String) = flow {
        try {
            emit(Loading)
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            val authResult = auth.signInWithCredential(credential).await()
            authResult.additionalUserInfo?.apply {
                emit(Success(isNewUser))
            }
        } catch (e: Exception) {
            emit(Failure(e.message ?: "Unexpected error!"))
        }
    }


    suspend fun createUserInFireStore(
        name: String = auth.currentUser?.displayName!!,
    ) = flow {
        try {
            emit(Loading)
            auth.currentUser?.apply {
                usersReference.document(uid).set(
                    mapOf(
                        NAME to name,
                        EMAIL to email,
                        FAVOURITE to listOf<RecipesModel>()
                    )
                ).await().also {
                    emit(Success(it))
                }

            }
        } catch (exception: Exception) {
            emit(Failure(exception.message ?: "Unexpected error!"))
        }
    }

    suspend fun createUserInFireStoreh(name: String): Flow<Boolean> = callbackFlow {
        auth.currentUser?.apply {
            val eventDocument = usersReference.document(uid)

            val subscription = eventDocument.set(
                mapOf(
                    NAME to name,
                    EMAIL to email,
                    FAVOURITE to listOf<RecipesModel>()

                )
            ).addOnCompleteListener {
                offer(it.isSuccessful)
            }

            awaitClose { subscription.result }
        }
    }
}
