package com.example.tabkati.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthRegistrar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirestoreUserRepository {

    val user= FirebaseAuth.getInstance().currentUser
    var db = Firebase.firestore

//    db.collection("users")
//    .add(user)
//    .addOnSuccessListener { documentReference ->
//        Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
//    }
//    .addOnFailureListener { e ->
//        Log.w(TAG, "Error adding document", e)
//    }



}