package com.example.m10_timer_life_cycle

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.m10_timer_life_cycle.databinding.ActivityMainBinding

private const val c_timerValue = "timerValue"
private const val c_timerIsActive = "timerIsActive"


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var handler = Handler(Looper.getMainLooper())

    private var timerValue = 10
    private var timeCounter = 10
    private var timerIsActive = false

    private lateinit var timerThread: Thread
    private var timerThreadStop = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) {
            timerValue = savedInstanceState.getInt(c_timerValue)
            timerIsActive = savedInstanceState.getBoolean(c_timerIsActive)
            Log.d("msg", "onCreate: после изменения $timerValue")
            timeCounter = timerValue
            handler.post {
                updateTimer()
                if (timerIsActive) startTimer()
            }
        }

        handler.post {
            binding.slider.addOnChangeListener { _, value, _ ->
                timerValue = value.toInt()
                timeCounter = timerValue
                updateUI()
            }

            binding.buttonStart.setOnClickListener {
                startTimer()
            }
        }
    }

    private fun updateUI() {
        when (timerIsActive) {
            true -> {
                binding.slider.isEnabled = false
                binding.buttonStart.visibility = View.GONE
                binding.buttonStop.visibility = View.VISIBLE
            }

            false -> {
                binding.slider.isEnabled = true
                binding.buttonStop.visibility = View.GONE
                binding.buttonStart.visibility = View.VISIBLE
            }
        }
        binding.progressBar.max = binding.slider.value.toInt()
        binding.progressBar.progress = timeCounter
        binding.timer.text = timerValue.toString()
    }

    private fun updateTimer() {
        binding.timer.text = timeCounter.toString()
        binding.progressBar.progress = timeCounter
    }

    private fun startTimer() {
        timerThread = Thread {
            while (!timerThreadStop && timeCounter > 0) {
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

                binding.buttonStop.setOnClickListener {
                    timerIsActive = false
                    stopTimerThread()
                }

                timeCounter--

                handler.post {
                    updateTimer()
                }
            }
            if (timeCounter == 0) timerIsActive = false
            handler.post { finishTimer() }
        }

        timerIsActive = true
        handler.post { updateUI() }
        timerThread.start()
    }

    private fun finishTimer() {
        if (!timerIsActive) {
            Toast.makeText(this, "Timer Task Finished", Toast.LENGTH_SHORT).show()
        }
        timerValue = binding.slider.value.toInt()
        timeCounter = timerValue
        timerThreadStop = false
        handler.post { updateUI() }
    }

    private fun stopTimerThread() {
        timerThreadStop = true
        timerThread.interrupt()
    }

    override fun onPause() {
        super.onPause()
        try {
            stopTimerThread()
        } catch (ex: Exception) {
            Log.d("msg", "onPause: $ex")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(c_timerValue, timeCounter)
        outState.putBoolean(c_timerIsActive, timerIsActive)
        super.onSaveInstanceState(outState)
    }
}
