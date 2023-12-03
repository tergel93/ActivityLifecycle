package me.tergel.kits.lifecycle

import android.app.Activity
import androidx.annotation.RestrictTo
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry


val Activity.lifecycle: LifecycleOwner
    get() = ActivityLifecycle.obtain(this)

@get:RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
@set:RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
var Activity.state: Lifecycle.State
    get() = LifecycleStateRegistry.getState(this)
    set(value) = LifecycleStateRegistry.setState(this, value)

@RestrictTo(RestrictTo.Scope.LIBRARY)
internal object LifecycleStateRegistry {

    private lateinit var monitor: LifecycleMonitor

    private val states by lazy(LazyThreadSafetyMode.NONE) { mutableMapOf<Activity, Lifecycle.State>() }

    val lifecycles by lazy(LazyThreadSafetyMode.NONE) { mutableMapOf<Activity, ActivityLifecycle>() }

    fun setState(activity: Activity, newState: Lifecycle.State) {
        if (newState == Lifecycle.State.DESTROYED) {
            states.remove(activity)
            lifecycles.remove(activity)?.setState(newState)
        } else {
            states[activity] = newState
            lifecycles[activity]?.setState(newState)
        }
    }

    fun getState(activity: Activity): Lifecycle.State {
        return states[activity] ?: Lifecycle.State.DESTROYED
    }


    fun register(monitor: LifecycleMonitor) {
        if (this::monitor.isInitialized) {
            throw IllegalStateException("More than one LifecycleMonitor instance have been created")
        }
        this.monitor = monitor
    }

}

@RestrictTo(RestrictTo.Scope.LIBRARY)
internal class ActivityLifecycle(activity: Activity) : LifecycleOwner {

    companion object {
        fun obtain(activity: Activity): ActivityLifecycle {
            return LifecycleStateRegistry.lifecycles.getOrPut(activity) {
                ActivityLifecycle(activity)
            }
        }
    }

    private val lifecycleRegistry = LifecycleRegistry(this)

    override val lifecycle: Lifecycle get() = lifecycleRegistry

    fun setState(state: Lifecycle.State) {
        lifecycleRegistry.currentState = state
    }

    init {
        lifecycleRegistry.currentState = activity.state
    }

}