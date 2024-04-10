package pl.fboro.taski.feature_reminder.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BootCompletedReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action ==  Intent.ACTION_BOOT_COMPLETED) {

            println("Hello world I'm booted!")
        }
    }
}