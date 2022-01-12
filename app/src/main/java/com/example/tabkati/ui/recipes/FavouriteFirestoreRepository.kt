package com.example.tabkati.ui.recipes

import com.example.tabkati.domain.repository.UserFirestereRepository
import com.example.tabkati.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@ExperimentalCoroutinesApi
class FavouriteFirestoreRepository @Inject constructor(private val auth: FirebaseAuth, @Named(
    Constants.FAVOURITE) private val favouriteReference: CollectionReference) {





}
