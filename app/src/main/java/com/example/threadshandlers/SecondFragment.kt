package com.example.threadshandlers

import android.content.Context
import android.graphics.Color
import android.os.*
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.threadshandlers.databinding.FragmentSecondBinding
import kotlin.random.Random


class SecondFragment : Fragment() {

    private var handler = Handler(Looper.getMainLooper())

    private var pauseOffset: Long = 0

    private lateinit var binding: FragmentSecondBinding

    private lateinit var viewModel: MainViewModel

    private var chronometerIsRun: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        handler.post(object : Runnable {
            @RequiresApi(Build.VERSION_CODES.P)
            override fun run() {
                handler.postDelayed(this, 20000)
                setRandomColor()
            }
        })
        binding.startButton.setOnClickListener {
            if (viewModel.isRestart) {
                viewModel.count = 0
            }
            viewModel.running = true
            viewModel.startPI()
            if (!chronometerIsRun) {
                startChronometer()
                chronometerIsRun = true
            }

        }
        binding.stopButton.setOnClickListener {
            if (chronometerIsRun) {
                stopChronometer()
                chronometerIsRun = false
            }
            viewModel.stopPI()
            viewModel.isRestart = false
        }
        binding.restartButton.setOnClickListener {
            restartChronometer()
            viewModel.stopPI()
            viewModel.count = 0
            viewModel.isRestart = true
            viewModel.running = true
        }
        return binding.root
    }


    private fun startChronometer() {
        binding.chronometer.base = SystemClock.elapsedRealtime() - pauseOffset
        binding.chronometer.start()
    }

    private fun stopChronometer() {
        binding.chronometer.stop()
        pauseOffset = SystemClock.elapsedRealtime() - binding.chronometer.base
    }

    private fun restartChronometer() {
        binding.chronometer.base = SystemClock.elapsedRealtime()
        pauseOffset = 0
        startChronometer()
    }

    private fun setRandomColor() {
        val rnd = Random
        val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        binding.viewFragment.setBackgroundColor(color)
    }

}