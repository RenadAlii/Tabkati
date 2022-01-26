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
    const val THEME = "theme"
    const val TITLE = "title"
    const val BOOKMARKED = "bookmarked"


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
     const val API_KEY = "e5542afac9674732ac012ed7fa50d970"
         //"a749dc5c1de24b10a225a2a8334f76b8"
         //"f8cc66e0181e4f699147d4871c361439"
    //e5542afac9674732ac012ed7fa50d970
    //c963fb4bd1e645f79fe352ddcf096d72
    //0082aa2f93d449c2b77b8115f5ab2f4e

    const val TAG = "ModalBottomSheet"

}