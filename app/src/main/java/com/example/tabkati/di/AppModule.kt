package com.example.tabkati.di

import android.app.Application
import android.content.Context
import android.content.Intent
import com.example.tabkati.MainActivity
import com.example.tabkati.data.AuthFirebaseRemoteDataSource
import com.example.tabkati.data.FavoriteFirestoreDataSource
import com.example.tabkati.data.RecipeCategoriesPictureDataSource
import com.example.tabkati.data.UserInfoFirestoreRemoteDataSource
import com.example.tabkati.domain.useCase.*
import com.example.tabkati.repository.*
import com.example.tabkati.ui.login.AuthMainActivity
import com.example.tabkati.ui.splash.SplashActivity
import com.example.tabkati.utils.Constants
import com.example.tabkati.utils.Constants.AUTH_INTENT
import com.example.tabkati.utils.Constants.MAIN_INTENT
import com.example.tabkati.utils.Constants.SPLASH_INTENT
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
import javax.inject.Singleton


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
    fun provideAuthFirebaseRemoteDataSource(
        auth: FirebaseAuth,
        @Named(Constants.USERS_REF) usersReference: CollectionReference,
        googleSignInClient: GoogleSignInClient
    ): AuthFirebaseRemoteDataSource = AuthFirebaseRemoteDataSource(auth, usersReference, googleSignInClient)


    @Provides
    fun provideAuthRepo(
        dataSource: AuthFirebaseRemoteDataSource,
    ): AuthRepository =
        AuthRepository(dataSource)

    @Provides
    fun provideSplashRepo(
        auth: FirebaseAuth,
    ): SplashRepository = SplashRepository(auth)

    @ExperimentalCoroutinesApi
    @Provides
    fun provideUserUseCases(repository: UserInfoFirestoreRemoteDataSource) = UserUseCase(
        getUserInfo = GetUserUseCase(repository), editUserName = EditUserNameUseCase(repository))

    @Provides
    fun provideBookMarkedUseCases(repository: FavoriteFirestoreDataSource) = BookmarkedUseCase(
    addBookMarked = AddFavoriteRecipeUseCase(repository), getBookMark = GetFavoriteRecipesUseCase(repository),
    deleteBookMarked = DeleteFavoriteRecipesUseCase(repository))


    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Named(SPLASH_INTENT)
    fun provideSplashIntent(context: Context): Intent {
        return Intent(context, SplashActivity::class.java)
    }

    @Provides
    @Named(AUTH_INTENT)
    fun provideAuthIntent(context: Context): Intent {
        return Intent(context, AuthMainActivity::class.java)
    }

    @Provides
    @Named(MAIN_INTENT)
    fun provideMainIntent(context: Context): Intent {
        return Intent(context, MainActivity::class.java)
    }


    @Provides
    @Singleton
    fun provideRecipeCategoriesPictureDataSource(
    ): RecipeCategoriesPictureDataSource =
        RecipeCategoriesPictureDataSource

    @Provides
    @Singleton
    fun provideRecipeCategoriesRepository(
        dataSource: RecipeCategoriesPictureDataSource
    ): RecipeCategoriesRepository =
        RecipeCategoriesRepository(dataSource)


}