package com.example.tabkati

import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.*
import com.example.tabkati.workers.RefreshDataWorker
import com.example.tabkati.workers.RefreshDataWorker.Companion.WORK_NAME
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject


// triggers Hilt's code generation.
@HiltAndroidApp
class TabkatiAplication: Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    @Inject
    lateinit var context: Context

    override fun getWorkManagerConfiguration() =
        Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()



   private val applicationScope = CoroutineScope(Dispatchers.Default)


    /* because onCreate is called before first screen is shown to user;
     use it to setup any background tasks, running expensive setup operations in
     background thread to avoid delaying app start.*/

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
       delayedInit()

    }

    private fun delayedInit() {
        applicationScope.launch {
            setupRecurringWork()
        }
    }

    private fun setupRecurringWork() {
        /*  constraints to prevent the work from occurring
         when there is no network or the device is low on battery.
         */
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresBatteryNotLow(true)
            .build()

        // schedule the work.
        val repeatingRequest = PeriodicWorkRequestBuilder<RefreshDataWorker>(1,TimeUnit.DAYS)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest
        )
    }


}