package com.example.tabkati.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.tabkati.R
import com.example.tabkati.repository.RecipesRepository
import com.example.tabkati.utils.makeStatusNotification
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import retrofit2.HttpException

// RefreshDataWorker to refresh the Recipes in the DB.
// CoroutineWorker to use Coroutines in worker.
@HiltWorker
class RefreshDataWorker  @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val repository: RecipesRepository) :
    CoroutineWorker(appContext, workerParams) {

    companion object{
        // work name.
        const val WORK_NAME ="RefreshDataWorker"
    }


    override suspend fun doWork(): Result {


        // Makes a notification when the work starts.
        makeStatusNotification(R.string.notification.toString() , applicationContext)

        return try {
            repository.refreshRecipes()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()

        }

    }
}