package com.example.tabkati.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class RecipesEntity(
    @PrimaryKey val id: Int?,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "image")
    val image: String?,
    @ColumnInfo(name = "servings")
    val servings: Int?,
    @ColumnInfo(name = "aggregateLikes")
    val aggregateLikes: Int?,
    @ColumnInfo(name = "readyInMinutes")
    val readyInMinutes: Int?,
    @ColumnInfo(name = "sourceUrl")
    val sourceUrl: String?,
    @ColumnInfo(name = "dairyFree")
    val dairyFree: Boolean?,
    @ColumnInfo(name = "vegetarian")
    val vegetarian: Boolean?,
    @ColumnInfo(name = "veryHealthy")
    val veryHealthy: Boolean?,
    @ColumnInfo(name = "glutenFree")
    val glutenFree: Boolean?,
    @ColumnInfo(name = "instructions")
    val instructions: String?,
)


//helper to converts from database objects to domain objects
//fun List<RecipesEntity>.asDomainModel(): List<RecipesModel> {
//    return map {
//        RecipesModel(
//            id = it.id,
//            title = it.title,
//            image = it.image,
//            servings = it.servings,
//            aggregateLikes = it.aggregateLikes,
//            readyInMinutes = it.readyInMinutes,
//            sourceUrl = it.sourceUrl,
//            instructions = it.instructions,
//        )
//    }
//}
