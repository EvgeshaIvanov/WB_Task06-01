package com.example.threadshandlers

import android.os.SystemClock
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val counterPI = MutableLiveData<String>()

    private var thread: Thread? = null

    var running = true

    var count = 0

    var isRestart = false

    fun startPI() {
        running = true
        if (thread?.isAlive == true) {
            stopPI()
        }
        thread = Thread {
            while (running) {
                counterPI.postValue("3.14${count++}")
                SystemClock.sleep(100)
            }
        }
        thread!!.start()
    }

    fun stopPI() {
        running = false
        if (!running) {
            thread!!.interrupt()
        }
    }

}