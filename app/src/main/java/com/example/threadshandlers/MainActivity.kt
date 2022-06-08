package com.example.threadshandlers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity(){
    /*
    a) Handler - это механизм, который позволяет работать с очередью сообщений.
    Он привязан к конкретному потоку (thread) и работает с его очередью.

    Handler дает нам две интересные и полезные возможности:
    1) реализовать отложенное по времени выполнение кода
    2) выполнение кода не в своем потоке

    Handler помещает сообщения в очередь.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            val fragmentManager: FragmentManager = supportFragmentManager
            fragmentManager.beginTransaction().replace(R.id.fragment_one, FirstFragment()).commit()
            fragmentManager.beginTransaction().replace(R.id.fragment_two, SecondFragment()).commit()
        }
    }


}