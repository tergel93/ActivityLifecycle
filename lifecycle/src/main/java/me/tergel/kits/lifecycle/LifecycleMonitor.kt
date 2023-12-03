package me.tergel.kits.lifecycle

import android.app.Activity
import android.os.Bundle
import androidx.lifecycle.Lifecycle

class LifecycleMonitor : SimpleActivityLifecycleCallback() {

    init {
        LifecycleStateRegistry.register(this)
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        activity.state = Lifecycle.State.CREATED
    }

    override fun onActivityStarted(activity: Activity) {
        activity.state = Lifecycle.State.STARTED
    }

    override fun onActivityResumed(activity: Activity) {
        activity.state = Lifecycle.State.RESUMED
    }

    override fun onActivityDestroyed(activity: Activity) {
        activity.state = Lifecycle.State.DESTROYED
    }

}