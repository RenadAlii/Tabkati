package com.example.tabkati.repository

import com.example.tabkati.data.Response.*
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

// Singleton to create one instanse of this class.
//@Singleton
//class AuthRepository @Inject constructor(
//    private val auth: CollectionReference,
//    private val usersReference: CollectionReference
//) {
//
//    suspend fun firebaseSignInWithGoogle(idToken: String) = flow {
//        try {
//            emit(Loading)
//            val credential = GoogleAuthProvider.getCredential(idToken, null)
//            val authResult = auth.signInWithCredential(credential).await()
//            authResult.additionalUserInfo?.apply {
//                emit(Success(isNewUser))
//            }
//        } catch (e: Exception) {
//            emit(Failure(e.message ?: "Unexpected error!"))
//        }
//    }
//
//    suspend fun createUserInFireStore() = flow {
//        try {
//            emit(Loading)
//            auth.currentUser?.apply {
//                usersReference.document(uid).set(
//                    mapOf(
//                        "name" to displayName,
//                        "email" to email
//                    )
//                ).await().also {
//                    emit(Success(it))
//                }
//
//            }
//        } catch (exception: Exception) {
//            emit(Failure(exception.message ?: "Unexpected error!"))
//        }
//    }
//}
