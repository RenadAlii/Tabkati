package com.example.tabkati.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredients")
data class ExtendedIngredientsLocalDataSource(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val recipeID: Int,
    @ColumnInfo(name = "nameClean")
    val name: String,
    @ColumnInfo(name = "unit")
    val unit: String,
    @ColumnInfo(name = "amount")
    val readyInMinutes: Double,
)
