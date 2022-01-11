package com.example.tabkati.di

import com.example.tabkati.repository.UserInfoFirestoreRepository
import com.example.tabkati.utils.Constants.SHOPPING_LIST_REF
import com.example.tabkati.utils.Constants.USERS_REF
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
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
        fun provideUsersRef(db: FirebaseFirestore) = db.collection(USERS_REF)

    @Provides
    @Named(SHOPPING_LIST_REF)
    fun provideShoppingListRef(db: FirebaseFirestore) = db.collection(SHOPPING_LIST_REF)

   @Provides
   fun ProvideUserFirestoreRemoteDataSource(
       auth: FirebaseAuth,
       @Named(USERS_REF) usersReference: CollectionReference
       ) = UserInfoFirestoreRepository(auth, usersReference)

    }
