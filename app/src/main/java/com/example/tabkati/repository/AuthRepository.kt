package com.example.tabkati.repository

import com.example.tabkati.data.Response.*
import com.example.tabkati.utils.Constants.EMAIL
import com.example.tabkati.utils.Constants.NAME
import com.example.tabkati.utils.Constants.USERS_REF
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

 //Singleton to create one instanse of this class.
@Singleton
class AuthRepository @Inject constructor(
    private val auth: FirebaseAuth,
    @Named(USERS_REF) private val usersReference: CollectionReference
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
//     suspend fun firebaseSignInWithEmail(email: String,password: String) = flow {
//         try {
//
//                 FirebaseAuth.getInstance()
//                     .createUserWithEmailAndPassword(email.trim(), password.trim())
//                     .addOnCanceledListener {
//                         IsSuccess("canceled")
//                     }
//                     .addOnFailureListener {
//                         IsSuccess (it.message.toString())
//                     }
//                     .addOnSuccessListener {
//                         IsSuccess ("success ${it.user?.email}")
//                     }
//
//         } catch (e: Exception) {
//             emit(Failure(e.message ?: "Unexpected error!"))
//         }
//     }

    suspend fun createUserInFireStore(name: String = auth.currentUser?.displayName!!
    ) = flow {
        try {
            emit(Loading)
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
            emit(Failure(exception.message ?: "Unexpected error!"))
        }
    }
}
