package com.example.tabkati.di

import android.app.Application
import android.content.Context
import com.example.tabkati.utils.Constants.USERS_REF
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)


    object FirebaseModule {

        @Provides
        fun provideFirebaseAuthInstance() = FirebaseAuth.getInstance()


        @Provides
        fun provideFirebaseFirestore() = FirebaseFirestore.getInstance()

        @Provides
        @Named(USERS_REF)
        fun provideUsersRef(db: FirebaseFirestore) = db.collection("users")

    }
