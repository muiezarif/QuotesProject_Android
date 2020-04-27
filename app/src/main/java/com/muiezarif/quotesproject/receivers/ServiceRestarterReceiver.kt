package com.muiezarif.quotesproject.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.muiezarif.quotesproject.services.NotificationService

class ServiceRestarterReceiver: BroadcastReceiver() {
    override fun onReceive(c: Context?, i: Intent?) {
        c?.startService(Intent(c,NotificationService::class.java))
    }
}