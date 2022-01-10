package com.example.tabkati.repository


import android.util.Log
import com.example.tabkati.domain.repository.UserFirestereRepository
import com.example.tabkati.domain.use_case.GetUserUseCase
import com.example.tabkati.model.User
import com.example.tabkati.utils.Constants.USERS_REF

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton
import kotlin.math.log

@Singleton
@ExperimentalCoroutinesApi
class UserInfoFirestoreRepository @Inject constructor(private val auth: FirebaseAuth, @Named(
    USERS_REF) private val usersReference: CollectionReference): UserFirestereRepository {

    override fun getUserFromFirestore():Flow<User> = callbackFlow {
        auth.currentUser?.apply {
            val snapshotListener = usersReference.document(uid).addSnapshotListener{
                    snapshot, e ->
               if (snapshot!=null){
                    val user = snapshot.toObject(User::class.java)
                   if (user != null) {
                       trySend(user)
                   }

                }else{
                   Log.e("error", "getUserFromFirestore: ${e?.message} ", )
                }


            }
            awaitClose {
                snapshotListener.remove()
            }

        }
    }
}