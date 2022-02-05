package com.example.tabkati.repository

import com.example.tabkati.domain.repository.ShoppingFireStoreRepository
import com.example.tabkati.utils.Constants.SHOPPING_LIST_REF
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton


@Singleton
@ExperimentalCoroutinesApi
class ShoppingListFirestoreRepository @Inject constructor(private val auth: FirebaseAuth, @Named(
    SHOPPING_LIST_REF) private val shoppingListReference: CollectionReference
): ShoppingFireStoreRepository {


}