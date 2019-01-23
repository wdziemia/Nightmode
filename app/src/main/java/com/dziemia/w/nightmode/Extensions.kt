package com.dziemia.w.nightmode

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.PowerManager
import androidx.localbroadcastmanager.content.LocalBroadcastManager


fun Context.getPowerManager(): PowerManager {
    return getSystemService(Context.POWER_SERVICE) as PowerManager
}

fun Context.registerReceiverAction(action: String, block: (Context, Intent) -> Unit): BroadcastReceiver {
    val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            block.invoke(context, intent)
        }
    }

    registerReceiver(receiver, IntentFilter(action))

    return receiver
}


fun Context.registerLocalReceiverAction(action: String, block: (Context, Intent) -> Unit): BroadcastReceiver {
    val localBroadcastManager = LocalBroadcastManager.getInstance(this)

    val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            block.invoke(context, intent)
        }
    }

    localBroadcastManager.registerReceiver(receiver, IntentFilter(action))

    return receiver
}

fun Context.unregisterLocalReceiver(receiver: BroadcastReceiver) {
    LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver)
}

fun Context.sendLocalBroadcast(action: String) {
    LocalBroadcastManager.getInstance(this).sendBroadcast(Intent(action))
}
