package com.example.tabkati.data.database


import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDao {


    @Query("SELECT * FROM  recipes")
    suspend fun getRandomRecipes(): List<RecipesEntity>


//    @Query("select * from desert")
//    fun getDesertRecipes(): List<DatabaseRecipes>

   // @Transaction
//    @Query("SELECT * FROM recipes where id = :id")
//    suspend fun getRecipeWithIngredients (id: Int):List<RecipeWithIngredients>
//

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipes(vararg recipes: RecipesEntity)

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertIngredients(vararg Ingredients: RecipesEntity)



}