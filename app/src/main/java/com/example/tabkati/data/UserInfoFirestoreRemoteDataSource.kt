package com.example.tabkati.data

import com.example.tabkati.domain.repository.UserFirestereRepository
import com.example.tabkati.model.User
import com.example.tabkati.utils.Constants.USERS_REF
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@ExperimentalCoroutinesApi
class UserInfoFirestoreRemoteDataSource @Inject constructor(private val auth: FirebaseAuth, @Named(
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

                }

            }
            awaitClose {
                snapshotListener.remove()
            }

        }
    }

    override suspend fun editUserNameInFirestore(name: String?): Flow<Unit> = callbackFlow {
        try {
            auth.currentUser?.apply {
                usersReference.document(uid).update(mapOf("name" to name)).await()

            }
        } catch (exception: Exception) {

        }

    }







}


