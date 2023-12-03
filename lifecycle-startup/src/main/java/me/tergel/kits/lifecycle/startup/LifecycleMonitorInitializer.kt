package me.tergel.kits.lifecycle.startup

import android.app.Application
import android.content.Context
import androidx.annotation.RestrictTo
import androidx.startup.Initializer
import me.tergel.kits.lifecycle.LifecycleMonitor

@RestrictTo(RestrictTo.Scope.LIBRARY)
class LifecycleMonitorInitializer : Initializer<LifecycleMonitor> {
    override fun create(context: Context): LifecycleMonitor {
        context as Application

        val monitor = LifecycleMonitor()
        context.registerActivityLifecycleCallbacks(monitor)
        return monitor
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}