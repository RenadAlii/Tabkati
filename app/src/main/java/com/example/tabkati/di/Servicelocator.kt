package com.example.tabkati.di

import android.app.Application
import android.content.Intent
import com.example.tabkati.data.RecipesRemoteDataSource
import com.example.tabkati.network.RecipeApiService
import com.example.tabkati.repository.AuthRepository
import com.example.tabkati.repository.MainAuthRepository
import com.example.tabkati.repository.RecipesRepository
import com.example.tabkati.utils.Constants
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

object Servicelocator {

    fun provideAuthRepo(): AuthRepository =
        AuthRepository(FirebaseModule.provideFirebaseAuthInstance(),
            FirebaseModule.provideUsersRef(FirebaseModule.provideFirebaseFirestore()))

    fun RecipesRemoteDataSource(
        api: RecipeApiService,
        dispatcher: CoroutineDispatcher
    ): RecipesRemoteDataSource = RecipesRemoteDataSource(api, dispatcher)


    fun provideRecipesRepository( api: RecipeApiService,
                                  dispatcher: CoroutineDispatcher): RecipesRepository =
        RecipesRepository(RecipesRemoteDataSource(api, dispatcher))


}
