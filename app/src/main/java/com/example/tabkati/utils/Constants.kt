package com.example.tabkati.utils

import android.provider.Settings.Global.getString
import com.example.tabkati.R


// Notification Channel constants

// Name of Notification Channel for verbose notifications of background work
@JvmField val VERBOSE_NOTIFICATION_CHANNEL_NAME: CharSequence =
    "Verbose WorkManager Notifications"
const val VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION =
    "Shows notifications whenever work starts"

const val CHANNEL_ID = "VERBOSE_NOTIFICATION"
const val NOTIFICATION_ID = 1

object Constants {

    //References
    const val USERS_REF = "users"
    const val SHOPPING_LIST_REF = "shoppingList"
    const val FAVOURITE = "favourite"

    //Fields
    const val NAME = "name"
    const val EMAIL = "email"



    //args
    const val CATEGORYID = "idOfCat"
    const val RECIPEID = "recipe_id"

    //Intents
    const val SPLASH_INTENT = "splashIntent"
    const val AUTH_INTENT = "authIntent"
    const val MAIN_INTENT = "mainIntent"



    // firebase notification.
    // channel name.
    const val channelName = "com.example.tabkati"
    // channel id.
    const val channelId = "notification_channel"

    // API
     const val BASE_URL = "https://api.spoonacular.com"

    const val TAG = "ModalBottomSheet"

}
