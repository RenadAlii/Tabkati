package com.example.tabkati.di

import com.example.tabkati.data.RecipesRemoteDataSource
import com.example.tabkati.data.database.RecipesDao
import com.example.tabkati.network.RecipeApiService
import com.example.tabkati.repository.RecipesRepository
import com.example.tabkati.utils.Constants.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModel {


    @Provides
    @Singleton
    fun provideKotlinJsonAdapterFactory(): KotlinJsonAdapterFactory = KotlinJsonAdapterFactory()

    @Provides
    @Singleton
    fun provideMoshi(kotlinJsonAdapterFactory: KotlinJsonAdapterFactory): Moshi = Moshi.Builder()
        .add(kotlinJsonAdapterFactory)
        .build()

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory =
        MoshiConverterFactory.create(moshi)


    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient =
        OkHttpClient
            .Builder()
            .build()

    @Provides
    @Singleton
    fun provideRetrofitClient(
        okHttp: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory,
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(moshiConverterFactory)
        .client(okHttp)
        .baseUrl(BASE_URL)
        .build()


    @Provides
    fun provideDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    fun provideRecipesRemoteDataSource(recipeApiService: RecipeApiService) =
        RecipesRemoteDataSource(recipeApiService, provideDispatcher())


    @Provides
    @Singleton
    fun provideRecipesRepository(
        recipeApiService: RecipeApiService,
        recipesDao: RecipesDao,
    ): RecipesRepository =
        RecipesRepository(provideRecipesRemoteDataSource(recipeApiService), recipesDao)





}
