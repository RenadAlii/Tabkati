package com.example.tabkati.di

import com.example.tabkati.data.AuthFirebaseRemoteDataSource

object Servicelocator {

    fun provideAuthRepo(): AuthFirebaseRemoteDataSource =
        AuthFirebaseRemoteDataSource(FirebaseModule.provideFirebaseAuthInstance(),
            FirebaseModule.provideUsersRef(FirebaseModule.provideFirebaseFirestore()))
//
//    fun RecipesRemoteDataSource(
//        api: RecipeApiService,
//        dispatcher: CoroutineDispatcher
//    ): RecipesRemoteDataSource = RecipesRemoteDataSource(api, dispatcher)
//

//    fun provideRecipesRepository( api: RecipeApiService,
//                                  dispatcher: CoroutineDispatcher): RecipesRepository =
//        RecipesRepository(RecipesRemoteDataSource(api, dispatcher))


}