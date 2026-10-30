package com.airdroid.helper.utils

import android.content.Context
import android.os.Bundle
import android.util.Log
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

object AnalyticsHelper {

    private const val TAG = "AnalyticsHelper"

    fun logEvent(context: Context, eventName: String, params: Bundle? = null) {
        try {
            val analytics = Firebase.analytics
            analytics.logEvent(eventName, params)
        } catch (e: Exception) {
            Log.e(TAG, "Error logging event: ${e.message}")
        }
    }

    fun logScreenView(context: Context, screenName: String) {
        logEvent(context, "screen_view", Bundle().apply {
            putString("screen_name", screenName)
        })
    }
}