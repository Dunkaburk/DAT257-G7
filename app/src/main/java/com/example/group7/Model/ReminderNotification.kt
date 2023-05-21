package com.example.group7.Model

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context


class ReminderNotification:Application() {
    override fun onCreate() {
        super.onCreate()
        val channel = NotificationChannel(
            "channel_id",
            "Channel name",
            NotificationManager.IMPORTANCE_HIGH
        )
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)
    }
}