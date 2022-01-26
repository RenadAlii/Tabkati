package com.example.tabkati.repository

import com.example.tabkati.data.RecipeCategoriesPictureDataSource
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RecipeCategoriesRepository @Inject constructor(private val dataSource: RecipeCategoriesPictureDataSource){

    fun getCategoriesList() = dataSource.recipeCategoriesPictureList
}