package com.example.tabkati.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

object FirebaseModule {

    object FirebaseModule {


        fun provideFirebaseAuthInstance() = FirebaseAuth.getInstance()


        fun provideFirebaseFirestore() = FirebaseFirestore.getInstance()


        fun provideUsersRef(db: FirebaseFirestore) = db.collection("users")

    }
}