package com.example.tabkati.repository

import android.util.Log
import androidx.fragment.app.activityViewModels
import com.example.tabkati.data.Response.*
import com.example.tabkati.ui.login.AuthViewModel
import com.example.tabkati.utils.Constants.EMAIL
import com.example.tabkati.utils.Constants.NAME
import com.example.tabkati.utils.Constants.USERS_REF
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
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
class AuthRepository @Inject constructor(
    private val auth: FirebaseAuth,
    @Named(USERS_REF) private val usersReference: CollectionReference,
) {


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
            Log.d("ddd", "createUserInFireStore: ")
            auth.currentUser?.apply {
                usersReference.document(uid).set(
                    mapOf(
                        NAME to name,
                        EMAIL to email
                    )
                ).await().also {
                    emit(Success(it))
                }

            }
        } catch (exception: Exception) {
            Log.d("ddd", "createUserInFireStore: ${exception.toString()}")
            emit(Failure(exception.message ?: "Unexpected error!"))
        }
    }

    suspend fun createUserInFireStoreh(name: String): Flow<Boolean> = callbackFlow {

        Log.d("ddd", "createUserInFireStore: ")
        auth.currentUser?.apply {
            val eventDocument = usersReference.document(uid)

            val subscription = eventDocument.set(
                mapOf(
                    NAME to name,
                    EMAIL to email
                )
            ).addOnCompleteListener {
                offer(it.isSuccessful)
            }




            awaitClose { subscription.result }
        }
    }
}
