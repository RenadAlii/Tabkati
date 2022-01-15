package com.example.tabkati.data

import android.util.Log
import com.example.tabkati.domain.repository.Favorite
import com.example.tabkati.model.RecipesModel
import com.example.tabkati.model.User
import com.example.tabkati.utils.Constants
import com.example.tabkati.utils.Constants.FAVOURITE
import com.example.tabkati.utils.FirebaseMassage
import com.example.tabkati.utils.FirebaseMassage.Massage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class FavoriteFirestoreDataSource @Inject constructor(
    private val auth: FirebaseAuth,
    @Named(
        Constants.USERS_REF) private val userReference: CollectionReference,
) : Favorite {

    override fun getFavoriteRecipesFromFirestore(): Flow<List<RecipesModel>> = callbackFlow {
        auth.currentUser?.apply {
            userReference.document(uid).get().addOnCompleteListener {
                val user = it.result.toObject(User::class.java)
                trySend(user?.favourite?: emptyList())
            }

        }
        awaitClose { }
    }

    override suspend fun addFavoriteRecipesToFirestore(listOfBookmarked: List<RecipesModel>)  {
        var msg = ""
        try {
          //  emit(FirebaseMassage.Loading)
            auth.currentUser?.apply {
                val list = userReference.document(uid).update(FAVOURITE, listOfBookmarked)
                    .addOnCompleteListener {
                //        msg = "Saved"

                    }.addOnCompleteListener {
                //        msg = it.exception?.message.toString()
                    }.addOnFailureListener {
                  //      msg = it.message.toString()

                    }
               // emit(Massage(msg))

            }} catch (e: Exception) {
              // emit(Massage(msg ?: e.message.toString()))
            }
            }


    override suspend fun deleteFavoriteRecipesFromFirestore(listWithoutDeletedRecipe: List<RecipesModel?>?) {
        var msg = ""
        try {
          //  emit(FirebaseMassage.Loading)
            auth.currentUser?.apply {
                auth.currentUser?.apply {
                    val list = userReference.document(uid).update(FAVOURITE, listWithoutDeletedRecipe)
                        .addOnCompleteListener {
                           // msg = "Deleted"
                        }.addOnCompleteListener {
                         //   msg = it.exception?.message.toString()
                        }.addOnFailureListener {
                        //    msg = it.message.toString()

                        }
                   // emit(Massage(msg))

                }

            }

        } catch (e: Exception) {
          //  emit(Massage(msg ?: e.message.toString()))

        }
    }
}