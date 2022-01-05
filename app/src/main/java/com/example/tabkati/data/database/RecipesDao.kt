package com.example.tabkati.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RecipesDao {


    @Query("select * from recipes")
    fun getRandomRecipes(): LiveData<List<DatabaseRecipes>>

//    @Query("select * from desert")
//    fun getDesertRecipes(): List<DatabaseRecipes>



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg videos: DatabaseRecipes)




}