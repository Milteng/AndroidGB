package com.example.m10_timer_life_cycle

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.m10_timer_life_cycle.databinding.ActivityMainBinding

private const val c_timerValue = "timerValue"
private const val c_timerIsActive = "timerIsActive"


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var handler = Handler(Looper.getMainLooper()) //для синхронизации с онсовным потоком

    private var timerValue = 10 // значение таймера
    private var timeCounter = 10 // количество по секундам
    private var timerIsActive = false // активность таймера
    private var timerThreadStop = false // флаг остановки потока

    private lateinit var timerThread: Thread // поток таймера



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.slider.addOnChangeListener { _, value, _ ->
            timerValue = value.toInt()
            timeCounter = timerValue
            updateEnableIFace()
        }

        binding.buttonStart.setOnClickListener {
            timerExecute()
        }

        //если восстанавливаем работу активити
        if (savedInstanceState != null) {
            timerValue = savedInstanceState.getInt(c_timerValue)
            timerIsActive = savedInstanceState.getBoolean(c_timerIsActive)
            timeCounter = timerValue

            updateTimer()

            if (timerIsActive) timerExecute()
        }

    }

    //функция обновления состояния элементов интерфейса
    private fun updateEnableIFace() {
        when (timerIsActive) {
            true -> { //если поток таймера активен, не сбрасываем активность элементов
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

    //функция обновления по значению таймера
    private fun updateTimer() {
        binding.timer.text = timeCounter.toString()
        binding.progressBar.progress = timeCounter
    }



    fun timerExecute() {
        timerThread = Thread(){
            while (!timerThreadStop && timeCounter > 0) {
                Thread.sleep(1000) //делаем задержку в секунду
                //навесим обработчик клика для завершения потока

                binding.buttonStop.setOnClickListener {
                    timerIsActive = false
                    stopTimerThread()
                }
                //уменьшили счетчик
                timeCounter--
                //нужно отобразить все это дело в интерфейсе
                handler.post {
                    updateTimer()
                }



            }

            if (timeCounter == 0) timerIsActive = false
            //сюда дошли по идее если цикл завершился
            //восстановим значения
            handler.post{   timerValue = binding.slider.value.toInt()
                            timeCounter = timerValue
                            timerThreadStop = false
                            timerIsActive = false
                            updateEnableIFace()
            }
        }


        timerIsActive = true;
        handler.post{updateEnableIFace()}
        timerThread.start()

    }

    private fun stopTimerThread() {
        timerThreadStop = true
        //timerThread.interrupt()
    }

    override fun onPause() {
        super.onPause()
        stopTimerThread()
    }

   //сохраняем значения переменных для возобновления потока после восстановления активити
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(c_timerValue, timeCounter)
        outState.putBoolean(c_timerIsActive, timerIsActive)
        super.onSaveInstanceState(outState)
    }
}