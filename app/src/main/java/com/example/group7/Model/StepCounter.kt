package com.example.group7.Model

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import androidx.lifecycle.MutableLiveData

class StepCounter(context: Context) : SensorEventListener {
    private var stepCounter: Int = 0
    var initialStepCount = 0
    private val context = context
    val currentNumberOfStepCount = MutableLiveData(0)




    private fun setupStepCounterListener() {
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        stepCounterSensor ?: return
        sensorManager.registerListener(this@StepCounter, stepCounterSensor, SensorManager.SENSOR_DELAY_FASTEST)
    }

    private fun startTracking() {
        setupStepCounterListener()
    }




    override fun onSensorChanged(sensorEvent: SensorEvent?) {
        Log.d("TAG", "onSensorChanged")
        sensorEvent ?: return
        val firstSensorEvent = sensorEvent.values.firstOrNull() ?: return
        Log.d("TAG", "Steps count: $firstSensorEvent ")
        val isFirstStepCountRecord = currentNumberOfStepCount.value == 0
        if (isFirstStepCountRecord) {
            initialStepCount = firstSensorEvent.toInt()
            currentNumberOfStepCount.value = 1
        } else {
            currentNumberOfStepCount.value = firstSensorEvent.toInt() - initialStepCount
        }    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        Log.d("TAG", "onAccuracyChanged: Sensor: $sensor; accuracy: $accuracy")
    }

}