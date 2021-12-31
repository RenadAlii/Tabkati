package com.example.tabkati.network

import com.example.tabkati.data.Response
import com.example.tabkati.data.SpoonacularRemoteDatasource
import com.example.tabkati.utils.Constants.API_KEY
import com.example.tabkati.utils.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query




interface RecipeApiService {

    //Get Random list of Recipes.
    @GET("/recipes/random?number={number_of_pages}&apiKey=${API_KEY}")
    suspend fun getRandomRecipes(@Query("number_of_pages") numberOfPage: String): SpoonacularRemoteDatasource

    //Get Recipe by id.
    @GET("/recipes/{id}/information?includeNutrition=false&apiKey=${API_KEY}")
    suspend fun getRecipe(@Path("id") RecipeId: String): Response

    //Get Recipes by category. /recipes/random?number=40&tags=desser
    @GET("/recipes/random?number=40&tags={category}&apiKey=${API_KEY}")
    suspend fun getRecipesByCategory(@Query("category") category: String): SpoonacularRemoteDatasource

    companion object {
        fun create():RecipeApiService{
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            // Build  Moshi object that Retrofit will use.
             val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

            // this is Retrofit builder to build and create a Retrofit object.
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .client(client)
            .build()
            .create(RecipeApiService::class.java)
        }


    }
}





