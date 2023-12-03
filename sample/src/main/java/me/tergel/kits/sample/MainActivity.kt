package me.tergel.kits.sample

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.tergel.kits.lifecycle.coroutine.lifecycleScope

private const val TAG = "MainActivity"

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.start).setOnClickListener {
            this.lifecycleScope.launch {
                while (true) {
                    Log.d(TAG, "tick ${System.currentTimeMillis() / 1000}")
                    delay(1000)
                }
            }
        }

        findViewById<View>(R.id.stop).setOnClickListener {
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "OnDestroy()")
    }
}