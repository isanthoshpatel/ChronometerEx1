package com.example.chronometerex1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var isRunning = false
    var offsetTime: Long? = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chronometer.format="Time:%s"
        chronometer.base = SystemClock.elapsedRealtime()
        chronometer.setOnChronometerTickListener {
            if(SystemClock.elapsedRealtime()-chronometer.base >= 10000){
                Toast.makeText(this,"10 sec completed!!",Toast.LENGTH_LONG).show()
                chronometer.base = SystemClock.elapsedRealtime()
            }
        }
        bt_startpause.setOnClickListener {
            if (!isRunning) {
                chronometer.start()
                chronometer.base = SystemClock.elapsedRealtime() - offsetTime!!

                bt_startpause.text = "pause"
                isRunning = true
            }else{
                chronometer.stop()
                offsetTime = SystemClock.elapsedRealtime() - chronometer.base

                bt_startpause.text = "start"
                isRunning = false
            }
        }
        bt_reset.setOnClickListener {
            chronometer.stop()
            chronometer.base = SystemClock.elapsedRealtime()
            offsetTime = 0


            bt_startpause.text = "start"
            isRunning = false
        }


    }
}
