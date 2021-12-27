package com.example.tabkati.di

import android.app.Application
import android.content.Intent
import com.example.tabkati.repository.AuthRepository
import com.example.tabkati.repository.MainAuthRepository
import com.example.tabkati.utils.Constants
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import dagger.Provides
import javax.inject.Named

object Servicelocator {

    fun provideGoogleSignInOptions(): GoogleSignInOptions {
        return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("185765143475-6bniaqlrg8ejrvf0vqgi6tu4huqpnmoi.apps.googleusercontent.com")
            .requestEmail()
            .build()
    }



    fun provideGoogleSignInClient(application: Application,
                                  options: GoogleSignInOptions
    ): GoogleSignInClient {
        return GoogleSignIn.getClient(application, options)
    }



    fun provideSignInIntent(googleSignInClient: GoogleSignInClient): Intent {
        return googleSignInClient.signInIntent
    }


    fun provideMainAuthRepo(googleSignInClient: GoogleSignInClient,
                            auth: FirebaseAuth
    ): MainAuthRepository =
        MainAuthRepository(googleSignInClient,auth)


    fun provideAuthRepo(): AuthRepository = AuthRepository(FirebaseModule.provideFirebaseAuthInstance(),FirebaseModule.provideUsersRef(FirebaseModule.provideFirebaseFirestore()))
}