package pl.fboro.taski.feature_reminder.utils

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import pl.fboro.taski.MainActivity
import pl.fboro.taski.R

class ReminderNotificationService(
    private val context: Context
) {

    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showNotification(id: Int, title: String, dueDate: String){
        val activityIntent = Intent(context, MainActivity::class.java)
        val activityPendingIntent = PendingIntent.getActivity(
            context,
            id,
            activityIntent,
            PendingIntent.FLAG_IMMUTABLE
        )
        val notification = NotificationCompat.Builder(context, REMINDER_CHANNEL_ID)
            .setSmallIcon(R.drawable.pen)
            .setContentTitle(title)
            .setContentText("${context.resources.getString(R.string.notificationLabel)} \"$title\": $dueDate")
            .setContentIntent(activityPendingIntent)
            .build()

        notificationManager.notify(id, notification)
    }

    companion object {
        const val REMINDER_CHANNEL_ID = "reminder_channel"
    }

}