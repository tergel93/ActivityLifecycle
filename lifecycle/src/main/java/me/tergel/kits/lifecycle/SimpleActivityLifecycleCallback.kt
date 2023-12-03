package me.tergel.kits.lifecycle

import android.app.Activity
import android.app.Application.ActivityLifecycleCallbacks
import android.os.Bundle
import android.util.Log
import androidx.annotation.RestrictTo

private const val TAG = "lifecycle"

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
open class SimpleActivityLifecycleCallback : ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        Log.d(TAG, "$activity, onActivityCreated")
    }

    override fun onActivityStarted(activity: Activity) {
        Log.d(TAG, "$activity, onActivityStarted")
    }

    override fun onActivityResumed(activity: Activity) {
        Log.d(TAG, "$activity, onActivityResumed")
    }

    override fun onActivityPaused(activity: Activity) {
        Log.d(TAG, "$activity, onActivityPaused")
    }

    override fun onActivityStopped(activity: Activity) {
        Log.d(TAG, "$activity, onActivityStopped")
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        Log.d(TAG, "$activity, onActivitySaveInstanceState")
    }

    override fun onActivityDestroyed(activity: Activity) {
        Log.d(TAG, "$activity, onActivityDestroyed")
    }

}