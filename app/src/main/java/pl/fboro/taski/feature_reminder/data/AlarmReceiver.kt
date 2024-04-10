package pl.fboro.taski.feature_reminder.data

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import pl.fboro.taski.feature_reminder.utils.ReminderNotificationService

class AlarmReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {

        val id = intent?.getIntExtra("ID", 0) ?: return
        val title = intent.getStringExtra("TITLE") ?: return
        val dueDate = intent.getStringExtra("DUE_DATE") ?: return

        val service = ReminderNotificationService(context)

        service.showNotification(id,  title, dueDate)
        println("Alarm triggered $title $dueDate")
    }

}