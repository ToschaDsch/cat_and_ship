package com.example.catandship

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.catandship.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var sManager: SensorManager

    var yAngle: Float = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSensor()
    }

    private fun initSensor() {
        val tvSensor = binding.tvSensor
        sManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensor = sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val sListener = object: SensorEventListener {
            override fun onSensorChanged(p0: SensorEvent?) {
                val value = p0?.values
                val sData = "X: ${value?.get(0)}\nY: ${value?.get(1)}\nZ: ${value?.get(2)}"
                yAngle = value?.get(1).toString().toFloat()
                tvSensor?.text = sData
            }
            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
            }
        }
        sManager.registerListener(sListener, sensor, SensorManager.SENSOR_DELAY_NORMAL)

    }
}