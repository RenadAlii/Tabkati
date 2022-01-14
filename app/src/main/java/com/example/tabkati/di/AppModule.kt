package com.example.tabkati.di

import android.app.Application
import android.content.Intent
import com.example.tabkati.data.FavoriteFirestoreDataSource
import com.example.tabkati.data.UserInfoFirestoreRemoteDataSource
import com.example.tabkati.domain.use_case.*
import com.example.tabkati.repository.*
import com.example.tabkati.utils.Constants
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Named


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
    fun provideGoogleSignInClient(
        application: Application,
        options: GoogleSignInOptions,
    ): GoogleSignInClient {
        return GoogleSignIn.getClient(application, options)
    }


    @Provides
    fun provideSignInIntent(googleSignInClient: GoogleSignInClient): Intent {
        return googleSignInClient.signInIntent
    }




    @Provides
    fun provideMainAuthRepo(
        googleSignInClient: GoogleSignInClient,
        auth: FirebaseAuth,
    ): MainAuthRepository =
        MainAuthRepository(googleSignInClient, auth)


    @Provides
    fun provideAuthRepo(
        auth: FirebaseAuth,
        @Named(Constants.USERS_REF) usersReference: CollectionReference,
    ): AuthRepository = AuthRepository(auth, usersReference)


    @ExperimentalCoroutinesApi
    @Provides
    fun provideUserUseCases(repository: UserInfoFirestoreRemoteDataSource) = UserUseCase(
        getUserInfo = GetUserUseCase(repository), editUserName = EditUserNameUseCase(repository))

    @Provides
    fun provideBookMarkedUseCases(repository: FavoriteFirestoreDataSource) = BookmarkedUseCase(
    addBookMarked = AddFavoriteRecipeUseCase(repository), getBookMark = GetFavoriteRecipesUseCase(repository),
    deleteBookMarked = DeleteFavoriteRecipesUseCase(repository))
}