package com.example.tabkati.data.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.tabkati.data.database.ExtendedIngredientsLocalDataSource
import com.example.tabkati.data.database.RecipesEntity

//data class RecipeWithIngredients (
//    @Embedded val recipe: RecipesEntity,
//    @Relation(
//        parentColumn = "id",
//        entityColumn =  "id"
//    )
//    val ingredients: List<ExtendedIngredientsLocalDataSource?>?
//)