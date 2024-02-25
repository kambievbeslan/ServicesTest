package com.beslan.servicestest

import android.app.IntentService
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat

class MyAnotherIntentService : IntentService(NAME) {

    override fun onCreate() {
        super.onCreate()
        log("onCreate")
        setIntentRedelivery(true)
    }

    override fun onHandleIntent(intent: Intent?) {
        log("onHandleIntent")
        val page = intent?.getIntExtra(PAGE, 0) ?: 0
        for (i in 0 until 100) {
            Thread.sleep(1000)
            log("Timer $i $page")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
    }

    private fun log(message: String) {
        Log.d("SERVICE_TAG", "MyAnotherIntentService: $message")
    }

    companion object {

        private const val NAME = "MyAnotherIntentService"
        private const val PAGE = "page"

        fun newIntent(context: Context, page: Int): Intent {
            return Intent(context, MyAnotherIntentService::class.java).apply {
                putExtra(PAGE, page)
            }
        }
    }
}