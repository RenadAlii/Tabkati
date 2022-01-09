package com.example.tabkati.di

import android.content.Context
import com.example.tabkati.data.database.AppDatabase
import com.example.tabkati.data.database.RecipesDao
import com.example.tabkati.data.database.getDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return getDatabase(context)
    }

    @Provides
    fun provideRecipeDao(appDatabase: AppDatabase): RecipesDao {
        return appDatabase.recipesDao
    }

}
