package com.hfad.myapplication999

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer

class MainActivity : AppCompatActivity() {

    val SAVED_TIME_KEY = "savedTime"
    val IS_RUNNING_KEY = "isRunning"


    lateinit var stopwatch:Chronometer
    var isRunning = false
    var savedTime:Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        stopwatch = findViewById(R.id.stopwatch)

        if (savedInstanceState!=null){
            savedTime = savedInstanceState.getLong(SAVED_TIME_KEY)
            isRunning = savedInstanceState.getBoolean(IS_RUNNING_KEY)
            stopwatch.base = savedInstanceState.getLong("1")
            if (isRunning) {
                stopwatch.start()
            } else {
                stopwatch.stop()
                stopwatch.base = SystemClock.elapsedRealtime() - savedTime
            }
        }


        val startButton = findViewById<Button>(R.id.startButton)
        startButton.setOnClickListener{
            if(!isRunning){
                stopwatch.base = SystemClock.elapsedRealtime() - savedTime
                stopwatch.start()
                isRunning = true
            }
        }


        val stopButton = findViewById<Button>(R.id.stopButton)
        stopButton.setOnClickListener{
            if(isRunning){
                savedTime = SystemClock.elapsedRealtime() - stopwatch.base
                stopwatch.stop()
                isRunning = false
            }
        }


        val resetButton = findViewById<Button>(R.id.resetButton)
        resetButton.setOnClickListener{
            stopwatch.stop()
            savedTime = 0
            stopwatch.base = SystemClock.elapsedRealtime()
            isRunning = false
        }


    }


    override fun onPause() {
       super.onPause()
        if(isRunning){
            savedTime = SystemClock.elapsedRealtime() - stopwatch.base
            stopwatch.stop()
        }
    }



    override fun onResume() {
        super.onResume()
        if(isRunning){
            stopwatch.base = SystemClock.elapsedRealtime() - savedTime
            stopwatch.start()
        }
    }



    override fun onRestart() {
        super.onRestart()
        if(isRunning){
            stopwatch.base = SystemClock.elapsedRealtime() - savedTime
            stopwatch.start()
        }
    }


    override fun onStop() {
        super.onStop()
        if(isRunning){
            savedTime = SystemClock.elapsedRealtime() - stopwatch.base
            stopwatch.stop()
        }

    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong("1", stopwatch.base)
        outState.putBoolean(IS_RUNNING_KEY, isRunning)
        outState.putLong(SAVED_TIME_KEY, savedTime)
    }


}






/*
stopwatch = findViewById<Chronometer>(R.id.stopwatch)

        val startButton = findViewById<Button>(R.id.startButton)
        startButton.setOnClickListener{
            if (!isRunning){
                setBaseTime()
                stopwatch.start()
                isRunning = true
            }
        }

        val stopButton = findViewById<Button>(R.id.stopButton)
        stopButton.setOnClickListener{
            if (isRunning){
                saveOffset()
                stopwatch.stop()
                isRunning = false
            }
        }

        val resetButton = findViewById<Button>(R.id.resetButton)
        resetButton.setOnClickListener{
            offset = 0
            setBaseTime()
        }





 fun setBaseTime(){
        stopwatch.base = SystemClock.elapsedRealtime() - offset
    }

    fun saveOffset(){
        offset = SystemClock.elapsedRealtime() - stopwatch.base
    }
*/