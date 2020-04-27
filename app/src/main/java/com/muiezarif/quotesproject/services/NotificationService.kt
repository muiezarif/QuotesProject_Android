package com.muiezarif.quotesproject.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.muiezarif.quotesproject.receivers.ServiceRestarterReceiver
import java.util.*


class NotificationService: Service() {
    private var timer: Timer? = null
    private var timerTask: TimerTask? = null
    var oldTime: Long = 0
    var counter = 0
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        Log.i("service","1")
        startTimer()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        var broadcastIntent = Intent(this, ServiceRestarterReceiver::class.java)
        sendBroadcast(broadcastIntent)
        stoptimertask()
    }

    fun startTimer() { //set a new Timer
        timer = Timer()
        //initialize the TimerTask's job
        initializeTimerTask()
        //schedule the timer, to wake up every 1 second
        timer?.schedule(timerTask, 1000, 1000) //
    }

    fun initializeTimerTask() {
        timerTask = object : TimerTask() {
            override fun run() {
                Log.i("in timer", "in timer ++++  " + counter++)
            }
        }
    }

    fun stoptimertask() { //stop the timer, if it's not already null
        if (timer != null) {
            timer!!.cancel()
            timer = null
        }
    }
}