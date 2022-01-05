package com.example.tabkati.data.database


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tabkati.data.*

@Entity(tableName = "drink")
data class DatabaseDrinkRecipes(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "servings")
    val servings: Int,
    @ColumnInfo(name = "aggregateLikes")
    val aggregateLikes: Int,
    @ColumnInfo(name = "readyInMinutes")
    val readyInMinutes: Int,
    @ColumnInfo(name="sourceUrl")
    val sourceUrl: String? = null,
    @ColumnInfo(name="ingredients")
    val ingredients: List<ExtendedIngredientsItem>,
    @ColumnInfo(name="steps")
    val steps: List<AnalyzedInstructionsItem?>?,
    @ColumnInfo(name = "dairyFree")
    val dairyFree: Boolean,
    @ColumnInfo(name = "vegetarian")
    val vegetarian: Boolean,
    @ColumnInfo(name = "veryHealthy")
    val veryHealthy: Boolean,
    @ColumnInfo(name = "glutenFree")
    val glutenFree: Boolean)


//converts from database objects to domain objects
fun List<DatabaseDrinkRecipes>.asDomainModel(): List<RecipesItem> {
    return map {
        RecipesItem(
            id = it.id,
            title = it.title,
            image = it.image,
            servings = it.servings,
            aggregateLikes = it.aggregateLikes,
            readyInMinutes = it.readyInMinutes,
            sourceUrl = it.sourceUrl,
            dairyFree = it.dairyFree,
            vegetarian = it.vegetarian,
            veryHealthy = it.veryHealthy,
            glutenFree = it.glutenFree,
            extendedIngredients = it.ingredients,
            analyzedInstructions = it.steps

        )
    }
}
