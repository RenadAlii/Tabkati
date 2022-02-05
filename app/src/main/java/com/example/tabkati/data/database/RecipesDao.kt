package com.example.tabkati.data.database


import androidx.room.*

@Dao
interface RecipesDao {

    @Query("SELECT * FROM  recipes")
    suspend fun getRandomRecipes(): List<RecipesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipes(vararg recipes: RecipesEntity)

}