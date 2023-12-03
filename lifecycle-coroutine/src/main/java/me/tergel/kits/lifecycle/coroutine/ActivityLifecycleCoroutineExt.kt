package me.tergel.kits.lifecycle.coroutine

import android.app.Activity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import me.tergel.kits.lifecycle.lifecycle

val Activity.lifecycleScope: CoroutineScope
    get() = this.lifecycle.lifecycleScope
