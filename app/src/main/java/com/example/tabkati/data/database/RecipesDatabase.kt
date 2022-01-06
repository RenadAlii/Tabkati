package com.example.tabkati.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//@Database(entities = [BreakfastRecipesEntity::class,MainCourseRecipesEntity::class,DrinkRecipesEntity::class,DesertRecipesEntity::class,
//        VegetarianRecipesEntity::class,RecipesEntity::class,SaladRecipesEntity::class,SideDishRecipesEntity::class,SnackRecipesEntity::class,
//    SoupRecipesEntity::class], version = 1, exportSchema = false)
//abstract class AppDatabase: RoomDatabase() {
//
//    // so the DB know about the Dao and the quires.
//    abstract fun recipeDao(): RecipesDao
//    companion object{
//        // because it will never be cached & all write & read will be done from main memory.
//        @Volatile
//        private  lateinit var INSTANCE: AppDatabase
//
//        fun getDatabase(context: Context): AppDatabase? {
//            synchronized(AppDatabase::class.java) {
//                if (!::INSTANCE.isInitialized) {
//                    INSTANCE = Room.databaseBuilder(context.applicationContext,
//                        AppDatabase::class.java,
//                        "videos").build()
//                }}
//
//
//
//        return INSTANCE
//    }}}
