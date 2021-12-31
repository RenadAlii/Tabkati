package com.example.tabkati.di

import com.example.tabkati.network.RecipeApiService
import com.example.tabkati.network.ServiceBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServicesModule {
    @Provides
    @Singleton
    fun provideRecipeService(serviceBuilder: ServiceBuilder): RecipeApiService = serviceBuilder.buildService(RecipeApiService::class.java)
}