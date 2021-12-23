package com.example.tabkati.di

import android.app.Application
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideGoogleSignInOptions(): GoogleSignInOptions {
        return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("185765143475-6bniaqlrg8ejrvf0vqgi6tu4huqpnmoi.apps.googleusercontent.com")
            .requestEmail()
            .build()
    }


    @Provides
    fun provideGoogleSignInClient(application: Application,
        options: GoogleSignInOptions
    ): GoogleSignInClient {
        return GoogleSignIn.getClient(application, options)
    }


    @Provides
    fun provideSignInIntent(googleSignInClient: GoogleSignInClient): Intent {
        return googleSignInClient.signInIntent
    }
}