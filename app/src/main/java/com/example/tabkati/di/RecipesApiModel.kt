package com.example.tabkati.di

import com.example.tabkati.data.RecipesRemoteDataSource
import com.example.tabkati.network.RecipeApiService
import com.example.tabkati.repository.RecipesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RecipesApiModel {

    @Singleton
    @Provides
    fun provideRecipeService(): RecipeApiService = RecipeApiService.create()

    @Provides
    fun provideDispatcher():CoroutineDispatcher=Dispatchers.IO

    @Provides
    fun RecipesRemoteDataSource(): RecipesRemoteDataSource = RecipesRemoteDataSource(provideRecipeService(), provideDispatcher())

    @Provides
    fun provideRecipesRepository(): RecipesRepository = RecipesRepository(RecipesRemoteDataSource())


}